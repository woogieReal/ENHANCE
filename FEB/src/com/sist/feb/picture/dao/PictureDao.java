/**
* <pre>
* com.sist.feb.picture.dao
* Class Name : PictureDao.java
* Description:
* Author: 
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
package com.sist.feb.picture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.picture.domain.PictureVO;
import com.sist.feb.post.cmn.PostDTO;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.post.domain.PostVO;

/**
 * @author khy81
 *
 */
public class PictureDao{
	
	final Logger LOG = Logger.getLogger(PictureDao.class);
	
	
	public PictureDao() {
	}


	/**
	 * img_group_seq 시퀀스 가져오기
	 * @param param
	 * @return
	 */
	public PictureVO getPicSeq(PostDTO param) {
		PictureVO picSeq =null;
		
		LOG.debug("getPicSeq()");
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		//Retrun Value처리 (ResultSet)
		ResultSet rs = null; //return값을 받기위해
		
		try {
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(20);
			sb.append(" SELECT img_group_seq.NEXTVAL seq \n");//그룹으로 가져오기
			sb.append(" FROM dual                 		    \n");	
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			rs = pstmt.executeQuery();
			LOG.debug("4. ResultSet: " + rs);
			
			if(rs.next() == true ) {
				picSeq = new PictureVO();
				picSeq.setPicGroup(rs.getInt("seq"));//알리아스로 가져오기
			}
			LOG.debug("5.picSeq"+picSeq);
			
		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		return picSeq;
	}
	
	/**
	 * 이미지 PK가져오기
	 * @param param
	 * @return pic_no
	 */
	public int setPicPK(PictureVO param) {
		int picPK = 0;
		
		PostVO postParam = new PostVO();

		//-------------------------------
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Retrun Value처리 (ResultSet)
		ResultSet rs = null; //return값을 받기위해
		
		try {
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(20);
			sb.append(" SELECT image_seq.NEXTVAL  \n");
			sb.append(" FROM dual                 \n");	
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			rs = pstmt.executeQuery();
			LOG.debug("4. ResultSet: " + rs);
			
			
		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		//-----------------------------------
		
		
		
		postParam.setPicNo(picPK);
		
		return picPK;
	}
	
	public PictureVO doSelectOne(PostDTO param) {
		PictureVO picture = null;
		PictureVO inVO = (PictureVO) param;
		
		LOG.debug("doSelectOne()");
		LOG.debug("0. param:" + inVO);
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Retrun Value처리 (ResultSet)
		ResultSet rs = null; //return값을 받기위해
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = ConnectionMaker.getConnection();
			
			StringBuffer sb = new StringBuffer(500);
			sb.append(" SELECT           \n");
			sb.append("     pic_no,      \n");
			sb.append("     pic_group,   \n");
			sb.append("     path,        \n");
			sb.append("     pic_nm,      \n");
			sb.append("     save_nm      \n");
			sb.append(" FROM             \n");
			sb.append("     picture      \n");
			sb.append(" WHERE pic_no = ? \n");
			
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1,inVO.getPicNo());
			
			LOG.debug("2.1 쿼리실행 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			rs = pstmt.executeQuery();
			LOG.debug("4. ResultSet: " + rs);
			
			if(rs.next() == true ) {
				picture = new PictureVO();
				
				picture.setPicNo(rs.getInt("pic_no"));
				picture.setPicGroup(rs.getInt("pic_group"));
				picture.setPath(rs.getString("path"));
				picture.setPicNm(rs.getString("pic_nm"));
				picture.setSaveNm(rs.getString("save_nm"));
			}
			
			LOG.debug("5.picture"+picture);
			
		}catch(SQLException e) {
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		return picture;
	}//--doSelectOne()
	
	
	
	public int doDelete(PostDTO param) {
		int flag =0;
		PictureVO inVO = (PictureVO) param;

		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = ConnectionMaker.getConnection();
			//오토커밋이 자동으로 작동되지 못하게
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(50);
			//2.쿼리 실행
			sb.append(" DELETE FROM picture    \n");
			sb.append(" WHERE pic_no= ?   	   \n");
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1,inVO.getPicNo());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
			connection.commit();// 성공 시 커밋
			
		}catch(SQLException e) {
			try {
				connection.rollback();
			}catch(SQLException e1) {
				LOG.debug("SQLException: " + e1.getMessage());
				e1.printStackTrace();
			}
			LOG.debug("SQLException: " + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return flag;
	}//--doDelete()
	
	
	public int doInsert(PostDTO param) {
		int flag =0;
		PictureVO inVO = (PictureVO) param;
		LOG.debug("0.param: "+inVO);
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			//1.jdbc드라이버로딩, DB접속
			connection = ConnectionMaker.getConnection();
			
			//2.쿼리 실행
			StringBuffer sb = new StringBuffer(1000);
			
			sb.append(" INSERT INTO picture ( \n");
			sb.append("     pic_no,           \n");
			sb.append("     pic_group,        \n");
			sb.append("     path,             \n");
			sb.append("     pic_nm,           \n");
			sb.append("     save_nm           \n");
			sb.append(" ) VALUES (            \n");
			sb.append("     IMAGE_SEQ.NEXTVAL,\n"); //IMAGE_SEQ.NEXTVAL
			sb.append("     ?,			  	  \n"); //IMG_GROUP_SEQ.NEXTVAL
			sb.append("     ?,                \n");
			sb.append("     ?,                \n");
			sb.append("     ?                 \n");
			sb.append(" )                     \n");
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			int i= 0;
			//pstmt.setInt(++i, inVO.getPicNo());
			pstmt.setInt(++i, inVO.getPicGroup());
			pstmt.setString(++i, inVO.getPath());
			pstmt.setNString(++i, inVO.getPicNm());
			pstmt.setNString(++i, inVO.getSaveNm());
			
			LOG.debug("2.1 sql 쿼리 PreparedStatement: \n" + sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement:" + connection);
			
			//4.쿼리 실행 결과 처리 (flag=1(성공),2(실패))
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리실행 flag: " + flag);
			
		}catch (SQLException e) {
			LOG.debug("SQLException:" + e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		return flag;
	}//--doInsert메서드

	public List<PictureVO> doInquirePictures(PostDTO param){
		List<PictureVO> list = new ArrayList<PictureVO>();
		
		PostPicVO inVO = (PostPicVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquirePictures 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("SELECT PIC_NO,        \n");
			sb.append("    PIC_GROUP,        \n");
			sb.append("    PATH || '/' || SAVE_NM AS PATH,  \n");
			sb.append("    PIC_NM,           \n");
			sb.append("    SAVE_NM           \n");
			sb.append("FROM picture          \n");
			sb.append("WHERE pic_group = ?   \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getPicGroup());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				PictureVO picture = new PictureVO();
				
				picture.setPicNo(rs.getInt("PIC_NO"));
				picture.setPicGroup(rs.getInt("PIC_GROUP"));
				picture.setPicNm(rs.getString("PIC_NM"));
				picture.setSaveNm(rs.getString("SAVE_NM"));
				
				String realPath = StringUtil.changePathForPost(rs.getString("PATH"));
				picture.setPath(realPath);
				
				list.add(picture);
				
				LOG.debug("realPath:"+realPath);
			}
			
			for(PictureVO vo : list) {
				LOG.debug(vo);
			}
			
			LOG.debug("=================조회건수"+list.size());
			
			connection.commit();
			
		}  catch(SQLException e) {
			LOG.debug("doInquirePost: "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return list;
	}

	
	
	

}//--class











