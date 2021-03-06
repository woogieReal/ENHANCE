/**
* <pre>
* com.sist.feb.category.dao
* Class Name : CategoryDao.java
* Description:
* Author: 임하람
* Since: 2021/03/08
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/08 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.category.dao;

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
import com.sist.feb.category.domain.CategoryVO;
import com.sist.feb.category.domain.SearchVO;
import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.cmn.StringUtil;


/**
 * @author 임하람
 *
 */
public class CategoryDao implements WorkStandard {
	final Logger LOG = Logger.getLogger(CategoryDao.class);
	
	public CategoryDao() { }//default 생성자
	
	/**
	 * category가 "UX_UI"인 게시물 목록을 불러오시오
	 * doCateRetrieve :카테고리 목록조회
	 * @return list
	 */
	@Override
	public List<CatePostVO> doCateRetrieve(DTO param) {
		
		List<CatePostVO> list = new ArrayList<CatePostVO>();
		SearchVO inVO = (SearchVO) param;
	
		LOG.debug("-CategoryDao--doCateRetrieve()-");
		LOG.debug("0.param:"+inVO);
		
		Connection connection =null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 :"+connection);
			
			
			StringBuffer sbWhere = new StringBuffer(500);//동적 검색조건 처리
			if(null != inVO && inVO.getSelectDiv() != "") {
				if(inVO.getSelectDiv().equals("ux_ui")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("3d_art")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("illustration")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("graphic")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("photography")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("architecture")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("product_design")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("advertisement")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("art")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}else if(inVO.getSelectDiv().equals("fashion")) {
					sbWhere.append("AND c.name like ? || '%' \n");
				}
			}//--if
			
			//3.쿼리
			StringBuffer sb = new StringBuffer(2000);
			sb.append("SELECT 															\n");
			sb.append("    c.CATEGORY_SEQ                                               \n");
			sb.append("    ,c.name                                      				\n");
			sb.append("    ,p.POST_NO                                                   \n");
			sb.append("    ,p.MEMBER_NO                                                 \n");
			sb.append("    ,m.name AS member_name                                       \n");
			sb.append("    ,p.TITLE                                                     \n");
			sb.append("    ,p.READ_CNT                                                  \n");
			sb.append("    ,p.pic_no                                                    \n");
			sb.append("    ,p1.pic_group                            					\n");
			sb.append("    ,p1.path || '/' ||p1.save_nm AS path  						\n");
			sb.append("FROM category c,post p,picture p1,member m                       \n");
			sb.append("WHERE c.category_seq = p.category_seq                            \n");
			sb.append("AND p.pic_no = p1.pic_no                                         \n");
			sb.append("AND p.pic_group = p1.pic_group               					\n");
			sb.append("AND p.member_no = m.member_no                                    \n");
			//sb.append("AND c.name = ?          				\n"); //동적쿼리로 수행!!
			//------------------------------------------------------
			//AND
			//-------------------------------------------------------
			//**********************************
			sb.append(sbWhere.toString());
			//**********************************
			sb.append("ORDER BY p.READ_CNT DESC             	\n");

			LOG.debug("2.1.쿼리실행 PreparedStatement :"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement : "+pstmt);
			
			//param set

			/*전체(조건 아무것도 없음)
			10=ux_ui
			20=3d_art
			30=illustration
			40=graphic
			50=photography 
			60=architecture
			70=product_design
			80=advertisement
			90=art
			100=fashion*/
			
			//카테고리 선택하면 ~~~!!!
			if(null != inVO && inVO.getSelectDiv() != "") {
			
				if(inVO.getSelectDiv().equals("ux_ui")
				  ||inVO.getSelectDiv().equals("3d_art")
				  ||inVO.getSelectDiv().equals("illustration")
				  ||inVO.getSelectDiv().equals("graphic")
				  ||inVO.getSelectDiv().equals("photography")
				  ||inVO.getSelectDiv().equals("architecture")
				  ||inVO.getSelectDiv().equals("product_design")
				  ||inVO.getSelectDiv().equals("advertisement")
				  ||inVO.getSelectDiv().equals("art")
				  ||inVO.getSelectDiv().equals("fashion")){
					
					//카테고리 구분
					pstmt.setString(1,inVO.getSelectDiv());
				}
				
			}//--if
			
			//-------param set end
			
			//쿼리실행
			rs = pstmt.executeQuery();
			LOG.debug("3.1. ResultSet :"+rs);
			
			while(rs.next() == true) {
				CatePostVO catePost = new CatePostVO();//return 객체
				
				catePost.setCategorySeq(rs.getInt("CATEGORY_SEQ"));
				catePost.setCateName(rs.getString("name"));
				catePost.setPostNo(rs.getInt("POST_NO"));
				catePost.setMemberNo(rs.getInt("MEMBER_NO"));
				catePost.setMemberName(rs.getString("member_name"));
				catePost.setTitle(rs.getString("TITLE"));
				catePost.setReadCnt(rs.getInt("READ_CNT"));
				catePost.setPicNo(rs.getInt("pic_no"));
				catePost.setPicGroup(rs.getInt("pic_group"));
				
				String realPath = StringUtil.changePathForPost(rs.getString("path"));
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




}//--class
