/**
* <pre>
* com.sist.feb.tag.dao
* Class Name : TagDao.java
* Description:
* Author: 
* Since: 2021/03/10
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/10 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.tag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.post.cmn.PostDTO;
import com.sist.feb.tag.domain.TagVO;

/**
 * @author khy81
 *
 */
public class TagDao {
	
	final Logger LOG = Logger.getLogger(TagDao.class);
	
	/**
	 * tag_group_seq 시퀀스 가져오기
	 * @param param
	 * @return
	 */
	public TagVO getTagSeq(PostDTO param) {
		TagVO tagSeq = null;
		
		LOG.debug("getTagSeq()");
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		//Retrun Value처리 (ResultSet)
		ResultSet rs = null; //return값을 받기위해
		
		try {
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(20);
			sb.append(" SELECT tag_group_seq.NEXTVAL seq \n");//그룹으로 가져오기
			sb.append(" FROM dual                 		    \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			rs = pstmt.executeQuery();
			LOG.debug("4. ResultSet: " + rs);
			
			if(rs.next() == true ) {
				tagSeq = new TagVO();
				tagSeq.setTagGroup(rs.getInt("seq"));//알리아스로 가져오기
			}
			LOG.debug("5. tagSeq: "+tagSeq);

		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return tagSeq;
	}

	
	public int doInsert(TagVO param) {
		int flag = 0;
		TagVO inVO = (TagVO) param;
		LOG.debug("0.tagName: "+param);
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(1000);
			sb.append(" INSERT INTO tag (    \n");
			sb.append("     tag_no,          \n");
			sb.append("     tag_group,       \n");
			sb.append("     name             \n");
			sb.append(" ) VALUES (           \n");
			sb.append("     ?, 				 \n");
			sb.append("     ?,               \n");
			sb.append("     ?                \n");
			sb.append(" )                    \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			int i= 0;
			pstmt.setInt(++i, inVO.getTagNo());
			pstmt.setInt(++i, inVO.getTagGroup());
			pstmt.setString(++i, inVO.getTagName());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
			
		}catch(SQLException e) {
			LOG.debug("SQLException:" + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		return flag;
	}

}
