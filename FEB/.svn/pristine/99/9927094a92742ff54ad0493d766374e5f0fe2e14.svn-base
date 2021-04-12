/**
* <pre>
* com.sist.feb.reply.dao
* Class Name : ReplyDao.java
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
package com.sist.feb.reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.reply.cmn.JDBCUtil;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.reply.cmn.ConnectionMaker;
import com.sist.feb.reply.cmn.DTO;
import com.sist.feb.reply.domain.ReplyMemberVO;
import com.sist.feb.reply.domain.ReplyVO;

/**
 * @author hansol
 *
 */
public class ReplyDao {

	final Logger LOG = Logger.getLogger(ReplyDao.class);
	
	public ReplyDao() {
		
		
	}
	

	
	
	/**
	 * 댓글 내용 수정
	 * @param param
	 * @return 성공 : 1, 실패 : 0
	 */
	public int doUpdate(DTO param) {
		int flag = 0;
		ReplyVO inVO = (ReplyVO) param;
		LOG.debug("0.param :"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			LOG.debug("2.데이터베이스 커넥션 구함:"+connection);
			
			StringBuffer sb = new StringBuffer(500);
			sb.append(" UPDATE reply                \n");
			sb.append(" SET contents = ?,          	\n");
			sb.append("     mod_dt = SYSDATE   		\n");
			sb.append(" WHERE reply_no =?                \n");
			
		
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setString(1,inVO.getContents());
			pstmt.setInt(2,inVO.getReply_no());
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("4. 쿼리 실행 flag : "+flag);
			
		}catch (SQLException e) {
			LOG.debug("SQLException:"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		
		return flag;
		
	
	}
	
	
	/**
	 * 게시물별 댓글 조회
	 * @param param
	 * @return list
	 */
	public List<ReplyMemberVO> checkReply(DTO param) {
		List<ReplyMemberVO> list = new ArrayList<ReplyMemberVO>();
		
		
		ReplyVO inVO = (ReplyVO) param;
		LOG.debug("0.param :"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			StringBuffer sb = new StringBuffer(500);
			sb.append(" SELECT r.reply_no,                                       \n");
			sb.append("        r.post_no,                                        \n");
			sb.append("        r.contents,                                       \n");
			sb.append("        r.mod_dt,                                         \n");
			sb.append(" 	   CASE WHEN TO_CHAR(SYSDATE,'YYYY/MM/DD') = TO_CHAR(r.reg_dt, 'YYYY/MM/DD')      ");
			sb.append("             THEN TO_CHAR(r.reg_dt,'HH24:MI')                                          ");
			sb.append("             ELSE TO_CHAR(r.reg_dt,'MM/DD') END reg_dt,                           ");
			sb.append("        r.member_no,                                        \n");
			sb.append("        m.name,                                        \n");
			sb.append("        m.email                                        \n");
			sb.append(" FROM reply r, member m                                            \n");
			sb.append(" WHERE r.member_no=m.member_no                                          \n");
			sb.append(" AND r.post_no=?                                           \n");
			sb.append(" ORDER BY reg_dt DESC                                     \n");
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3.  PreparedStatement  : "+pstmt);
			
			pstmt.setInt(1, inVO.getPost_no());
			rs = pstmt.executeQuery();
			LOG.debug("3.1  ResultSet  : "+rs);
			
			while(rs.next()==true) {
				ReplyMemberVO reply = new ReplyMemberVO();	
				
				//패스를 가져와서 전체 경로로 바꿈
				
				reply.setReply_no(rs.getInt("reply_no"));
				reply.setPost_no(rs.getInt("post_no"));
				reply.setContents(rs.getString("contents"));
				reply.setReg_dt(rs.getString("reg_dt"));
				reply.setMod_dt(rs.getString("mod_dt"));
				reply.setMember_no(rs.getInt("member_no"));
				reply.setName(rs.getString("name"));
				reply.setEmail(rs.getString("email"));
				
				String path = StringUtil.changePath(rs.getInt("member_no"));
				reply.setPath(path);
				
				list.add(reply);
			}
			
			for(ReplyMemberVO vo : list) {
				LOG.debug(vo);
			}
			
		}catch(SQLException e) {
			LOG.debug("SQLException"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		
		}
		
		return list;
	}
	
	/**
	 * 댓글 삭제
	 * @param param
	 * @return 성공 : 1, 실패 : 0
	 */
	public int doDelete(DTO param) {
		int flag = 0;
		
		ReplyVO inVO = (ReplyVO) param;
		LOG.debug("0.param:"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			connection = ConnectionMaker.getConnection();
			
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(50);
			sb.append("DELETE FROM reply \n");
			sb.append("WHERE reply_no =?     \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement  : "+pstmt);
			
			pstmt.setInt(1, inVO.getReply_no());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 실행 flag : "+flag);
			
			connection.commit();
			
		}catch (SQLException e) {
			LOG.debug("SQLException:"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return flag;
	}
	
	
	
	/**
	 * 댓글 등록
	 * @param param
	 * @return 성공 : 1, 실패 : 0
	 */
	public int doInsert(DTO param) {
		int flag = 0;
		ReplyVO inVO = (ReplyVO) param;
		LOG.debug("0.param:"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			connection = ConnectionMaker.getConnection();
			LOG.debug("2.데이터베이스 커넥션 구함:"+connection);
			
			StringBuffer sb = new StringBuffer(1000);
			sb.append("INSERT INTO REPLY (");
			sb.append("		reply_no,   \n ");
			sb.append("		post_no,    \n ");
			sb.append("		contents,   \n ");
			sb.append("		member_no   \n ");
			sb.append(")VALUES (        \n ");
			sb.append("		REPLY_SEQ.nextval,        \n ");
			sb.append("		?,        \n ");
			sb.append("		?,        \n ");
			sb.append("		?         \n ");
			sb.append(")              \n ");
		
			pstmt = connection.prepareStatement(sb.toString());
		

			pstmt.setInt(1,inVO.getPost_no());
			pstmt.setString(2,inVO.getContents());
			pstmt.setInt(3,inVO.getMember_no());
			
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
			LOG.debug("3. 쿼리실행  : "+connection);
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 실행 flag : "+flag);
			
		}catch (SQLException e) {
			LOG.debug("SQLException:"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		
		return flag;
		
	}
}
