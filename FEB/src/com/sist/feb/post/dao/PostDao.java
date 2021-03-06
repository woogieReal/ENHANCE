/**
* <pre>
* com.sist.feb.post.dao
* Class Name : PostDao.java
* Description:
* Author: 김혜영
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
package com.sist.feb.post.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.post.cmn.PostDTO;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.post.domain.PostVO;

/**
 * @author khy81
 *
 */


/* <참고 자료>
 * https://upcake.tistory.com/256?category=897762
 * https://riucc.tistory.com/category/JSP%20%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EA%B8%B0?page=1 
 * http://gurubee.net/article/80833
 */

public class PostDao{
	
	final Logger LOG = Logger.getLogger(PostDao.class);
	
	public PostDao() {
		
	}
	
	/**
	 * @param param
	 * @return post테이블의 pic_no
	 */
	public int getPicPK(PostVO param) {
		int picPK = 0;
		
		picPK = param.getPicNo();
		
		return picPK;
	}
	
	/**
	 * 
	 * @param param
	 * @return post테이블의 tag_group
	 */
	public int getTagPK(PostVO param) {
		int tagPK = 0;
		
		tagPK = param.getTagGroup();
		
		return tagPK;
	}
	
	public int doReadCnt(PostDTO param) {
		int flag =0;
		
		PostPicVO inVO = (PostPicVO)param;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(500);
			sb.append(" UPDATE post                       \n");
			sb.append(" SET read_cnt = NVL(read_cnt,0)+1  \n");
			sb.append(" WHERE post_no = ?                 \n");
			
			//3. 쿼리실행 PreparedStatement
			LOG.debug("2.1 쿼리실행 PreparedStatement: \n" + sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO.getPostNo());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			//6.prepareStatement자원반납
			//7.connection 반납
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return flag;
	}//--doReadCnt()
	
	public int doUpdate(PostDTO param) {
		int flag =0;

		PostVO inVO = (PostVO) param;
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
			
		try {
			
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(500);
			sb.append(" UPDATE post             \n");
			sb.append(" SET title = ?,          \n");
			sb.append("     contents = ?,       \n");
			sb.append("     mod_dt = SYSDATE    \n");
			sb.append(" WHERE post_no = ?		\n");
			
			//3. 쿼리실행 PreparedStatement
			LOG.debug("2.1 쿼리실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getTitle());    
			pstmt.setString(2, inVO.getContents());   
			pstmt.setInt(3, inVO.getPostNo());        
			
			flag = pstmt.executeUpdate(); //flag값 return
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return flag;
	}//--doUpdate()

	
//	public PostVO doSelectOne(PostDTO param) {
//		PostVO post = null;
//		PostVO inVO = (PostVO) param;
//		
//		LOG.debug("doSelectOne()");
//		LOG.debug("0. param:" + inVO);
//		Connection connection  = null;
//		PreparedStatement pstmt = null;
//		
//		//Retrun Value처리 (ResultSet)
//		ResultSet rs = null; //return값을 받기위해
//		
//		try {
//			connection = ConnectionMaker.getConnection();
//			
//			StringBuffer sb = new StringBuffer(1000);
//			sb.append(" SELECT                										 \n");
//			sb.append("     post_no,          										 \n");
//			sb.append("     member_no,        										 \n");
//			sb.append("     title,            										 \n");
//			sb.append("     contents,         										 \n");
//			sb.append("     read_cnt,         										 \n");
//			sb.append("     reg_dt,           										 \n");
//			sb.append("     TO_CHAR(mod_dt,'YYYY-MM-DD HH24:MI:SS')mod_dt,           \n");
//			sb.append("     pic_no,           										 \n");
//			sb.append("     pic_group,        										 \n");
//			sb.append("     category_seq,     										 \n");
//			sb.append("     tag_group         										 \n");
//			sb.append(" FROM                  										 \n");
//			sb.append("     post              										 \n");
//			sb.append(" WHERE                 										 \n");
//			sb.append("     post_no = ?       										 \n");
//			LOG.debug("2.1 쿼리실행 PreparedStatement: \n" + sb.toString());
//			
//			pstmt = connection.prepareStatement(sb.toString());
//			LOG.debug("3. 쿼리실행 PreparedStatement: " + pstmt);
//			pstmt.setInt(1, inVO.getPostNo());
//			
//			rs = pstmt.executeQuery();
//			LOG.debug("3.1. ResultSet : "+rs);
//			
//			if(rs.next() == true) { 
//				post = new PostVO(); //return BoardVO 객체 생성
//				
//				post.setPostNo(rs.getInt("post_no"));
//				post.setMemberNo(rs.getInt("member_no"));
//				post.setTitle(rs.getString("title"));
//				post.setContents(rs.getString("contents"));
//				post.setReadCnt(rs.getInt("read_cnt"));
//				post.setRegDt(rs.getString("reg_dt"));
//				post.setModDt(rs.getString("mod_dt"));
//				post.setPicNo(rs.getInt("pic_no"));
//				post.setPicGroup(rs.getInt("pic_group"));
//				post.setCategorySeq(rs.getInt("category_seq"));
//				post.setTagGroup(rs.getInt("tag_group"));
//				
//			}
//			LOG.debug("3.2. post : " + post);
//		}catch(SQLException e) {
//			LOG.debug("SQLException: " + e.getMessage());
//			e.printStackTrace();
//		}finally {
//			JDBCUtil.close(rs);
//			JDBCUtil.close(pstmt);
//			JDBCUtil.close(connection);
//		}
//		return post;
//	}//--doSelectOne()
	
	/**
	 * 이미지 경로 추가
	 * @param param
	 * @return
	 */
	public PostPicVO doSelectOne(PostDTO param) {
		PostPicVO post =null;
		PostPicVO inVO  = (PostPicVO) param;
		
		LOG.debug("doSelectOne()");
		LOG.debug("0. param:" + inVO);
		
		Connection connection  = null;
		PreparedStatement pstmt = null;
		
		//Retrun Value처리 (ResultSet)
		ResultSet rs = null; //return값을 받기위해
		
		try {
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(1000);
			sb.append(" SELECT t1.post_no,                                                \n");
			sb.append("        t1.member_no,                                              \n");
			sb.append("        t1.title,                                                  \n");
			sb.append("        t1.contents,                                               \n");
			sb.append("        t1.read_cnt,                                               \n");
			sb.append("        t1.reg_dt,                                                 \n");
			sb.append("        TO_CHAR(t1.mod_dt,'YYYY-MM-DD HH24:MI:SS')mod_dt,          \n");
			sb.append("        t1.pic_group,                                              \n");
			sb.append("        t1.pic_no,                                                 \n");
			sb.append("        t1.category_seq,                                           \n");
			sb.append("        t1.tag_group,                                              \n");
//			sb.append("        t3.name,                                                   \n");
			sb.append("        t2.path || '/' || t2.save_nm AS PATH                       \n");
			sb.append(" FROM post t1, picture t2                                          \n");
			sb.append(" WHERE t1.pic_group = t2.pic_group                                 \n");
//			sb.append(" AND t1.tag_group = t3.tag_group                                   \n");
			sb.append(" AND post_no = ?                                                   \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement: " + pstmt);
			pstmt.setInt(1, inVO.getPostNo());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1. ResultSet : "+rs);
			
			if(rs.next() == true ) {
				post = new PostPicVO(); //return PostPicVO 객체 생성
				
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberNo(rs.getInt("member_no"));
				post.setTitle(rs.getString("title"));
				post.setContents(rs.getString("contents"));
				post.setReadCnt(rs.getInt("read_cnt"));
				post.setRegDt(rs.getString("reg_dt"));
				post.setModDt(rs.getString("mod_dt"));
				post.setPicGroup(rs.getInt("pic_group"));
				post.setPicNo(rs.getInt("pic_no"));
				post.setCategorySeq(rs.getInt("category_seq"));
				post.setTagGroup(rs.getInt("tag_group"));
				//post.setTagName(rs.getString("name"));
//				post.setPath(rs.getString("PATH"));
				
				String changePath = StringUtil.changePathForPost(rs.getString("PATH"));
				post.setPath(changePath);
				
				
			}
			LOG.debug("3.2 post: "+post);
			
		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return post;
	}
	
	//Post삭제 메서드
	public int doDelete(PostDTO param) {
		int flag =0;
		PostPicVO inVO = (PostPicVO) param;
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = ConnectionMaker.getConnection();
			//오토커밋이 자동으로 작동되지 못하게
			connection.setAutoCommit(false);
			
			LOG.debug("삭제 게시물 번호: "+inVO.getPostNo());
			
			StringBuffer sb = new StringBuffer(100);
			//2.쿼리 실행
			sb.append(" DELETE FROM post    \n");
			sb.append(" WHERE post_no= ?   	\n");
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO.getPostNo());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
			connection.commit();// 성공 시 커밋
			
		} catch(SQLException e) {
			LOG.debug("doDeleteProfileImage:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
		
	}//--doDelete()
	
	//Post작성 메서드
	public int doInsert(PostDTO param) {
		int flag = 0;
		PostVO inVO = (PostVO) param;
		LOG.debug("0.param: "+param);
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		//PreparedStatement : binding sql을 쓴다. Statement보다 sql수행 속도가 우수
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = ConnectionMaker.getConnection();
			
			//2.쿼리 실행
			StringBuffer sb = new StringBuffer(1000);
			
			sb.append(" INSERT INTO post (     \n");
			sb.append("     post_no,           \n");
			sb.append("     member_no,         \n");
			sb.append("     title,             \n");
			sb.append("     contents,          \n");
			sb.append("     read_cnt,          \n");
			sb.append("     pic_no,            \n");
			sb.append("     pic_group,         \n");
			sb.append("     category_seq,      \n");
			sb.append("     tag_group          \n");
			sb.append(" ) VALUES (             \n");
			sb.append("     post_seq.nextval,  \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?,                 \n");
			sb.append("     ?                  \n");
			sb.append(" )					   \n");
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			//pstmt.setInt   (1, inVO.getPostNo());
			int i = 0;
			pstmt.setInt   (++i, inVO.getMemberNo());
			pstmt.setString(++i, inVO.getTitle());
			pstmt.setString(++i, inVO.getContents());
			pstmt.setInt   (++i, inVO.getReadCnt());
			pstmt.setInt   (++i, inVO.getPicNo());
			pstmt.setInt   (++i, inVO.getPicGroup());
			pstmt.setInt   (++i, inVO.getCategorySeq());
			pstmt.setInt   (++i, inVO.getTagGroup());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			
			//4.쿼리 실행 결과 처리 (flag=1(성공),2(실패))
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
		}catch(SQLException e) {
			LOG.debug("SQLException:" + e.getMessage());
			e.printStackTrace();
		}finally {// 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제한다
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		return flag;
	}//--doInsert메서드
	
	public int doInquireCurrval() {
		int count = 0;
		
		LOG.debug("doInquireCurrval");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(300);
			
			sb.append("SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'IMAGE_SEQ'");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				count = rs.getInt("LAST_NUMBER");
			}
			
			LOG.debug("count: " + count);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doInquireCurrval:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			JDBCUtil.close(rs);
			
		}
		
		return count;
	}
	
	
	
	
	
	
	
	
}//--class	




