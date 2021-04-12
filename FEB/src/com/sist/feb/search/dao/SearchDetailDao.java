/**
* <pre>
* com.sist.feb.search.dao
* Class Name : SearchDetailDao.java
* Description:
* Author: 임하람
* Since: 2021/03/23
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/23 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.search.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.category.cmn.DTO;
import com.sist.feb.category.cmn.WorkStandard;
import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.search.domain.SearchDetailVO;


/**
 * @author 123wo
 *
 */
public class SearchDetailDao implements WorkStandard{

	final Logger LOG = Logger.getLogger(SearchDetailDao.class);
	
	public SearchDetailDao() {
		
	}

	@Override
	public List<CatePostVO> doCateRetrieve(DTO param) {
		
		LOG.debug("----------------------------------------------");
		LOG.debug("==SearchDetailDao==doCateRetrieve()==");
		LOG.debug("----------------------------------------------");
		
		List<CatePostVO> list = new ArrayList<CatePostVO>();
		SearchDetailVO inVO = (SearchDetailVO) param;
		
		LOG.debug("-SearchDetailDao--doCateRetrieve()-");
		LOG.debug("0.param:"+inVO);
		
		Connection connection =null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 :"+connection);
			
			StringBuffer sbWhere = new StringBuffer(500);//동적 검색조건 처리
			if(null != inVO && inVO.getSearchDiv() != "") {
				
				if(inVO.getSearchDiv().equals("전체")) {
					sbWhere.append(" AND ( UPPER(p.TITLE) LIKE UPPER('%'|| ? ||'%') OR UPPER(m.name) LIKE UPPER('%'|| ? ||'%') )    \n"); 
					
				} else if(inVO.getSearchDiv().equals("제목")) {
					sbWhere.append(" AND UPPER(p.TITLE) LIKE UPPER('%'|| ? ||'%')   \n");
				
				} else if(inVO.getSearchDiv().equals("작성자")) {
					sbWhere.append(" AND UPPER(m.name) LIKE UPPER('%'|| ? ||'%')    \n");
				}
			}//--검색 분류 if
			
			StringBuffer sb = new StringBuffer(2000);
			sb.append(" SELECT 										         \n");
			sb.append("     c.CATEGORY_SEQ,                                  \n");
			sb.append("     c.name AS CATEGORY_NAME,                         \n");         
			sb.append("     p.POST_NO,                                       \n");
			sb.append("     p.MEMBER_NO,                                     \n");
			sb.append("     m.name AS MEMBER_NAME,                           \n");
			sb.append("     p.TITLE,                                         \n");
			sb.append("     p.READ_CNT,                                      \n");
			sb.append("     p.pic_no,                                        \n");
			sb.append("     p1.pic_group,                                    \n");
			sb.append("     p1.path || '/' ||p1.save_nm AS POST_IMAGE_PATH   \n");
			sb.append(" FROM category c, post p, picture p1, member m        \n");
			sb.append(" WHERE c.category_seq = p.category_seq                \n");
			sb.append(" AND p.pic_no = p1.pic_no                             \n");
			sb.append(" AND p.pic_group = p1.pic_group                       \n");
			sb.append(" AND p.member_no = m.member_no                        \n");
			
			sb.append(sbWhere.toString());
			
			sb.append(" ORDER BY p.READ_CNT DESC                             \n");
			
			LOG.debug("2.1.쿼리실행 PreparedStatement :"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement : "+pstmt);
			
			if(null != inVO && inVO.getSearchDiv() != "") {
				
				if(inVO.getSearchDiv().equals("전체")) {
					
					pstmt.setString(1, inVO.getSearchWord());
					pstmt.setString(2, inVO.getSearchWord());
					
				} else if(inVO.getSearchDiv().equals("제목") || inVO.getSearchDiv().equals("작성자")) {
					
					pstmt.setString(1, inVO.getSearchWord());
					
				} 
				
			}// param 세팅
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1. ResultSet :"+rs);
			
			while(rs.next() == true) {
				CatePostVO catePost = new CatePostVO();//return 객체
				
				catePost.setCategorySeq(rs.getInt("CATEGORY_SEQ"));
				catePost.setCateName(rs.getString("CATEGORY_NAME"));
				catePost.setPostNo(rs.getInt("POST_NO"));
				catePost.setMemberNo(rs.getInt("MEMBER_NO"));
				catePost.setMemberName(rs.getString("MEMBER_NAME"));
				catePost.setTitle(rs.getString("TITLE"));
				catePost.setReadCnt(rs.getInt("READ_CNT"));
				catePost.setPicNo(rs.getInt("pic_no"));
				catePost.setPicGroup(rs.getInt("pic_group"));
				
				String realPath = StringUtil.changePathForPost(rs.getString("POST_IMAGE_PATH"));
				catePost.setPath(realPath);
				
				list.add(catePost);
				
			}//--while
			
			//조회 데이터 확인
			LOG.debug("****************");
			for(CatePostVO vo:list) {
				LOG.debug(vo);
			}
			LOG.debug("****게시물 조회 데이터 사이즈****:"+list.size());
			
			connection.commit();
			
		}catch(SQLException e) {
			LOG.debug("SQLException"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}//--finally
		
		return list;
	}//--doCateRetrieve
	
	
	
	
	
	
	
}
