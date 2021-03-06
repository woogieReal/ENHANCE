/**
* <pre>
* com.sist.feb.member.dao
* Class Name : MemberLogInDao.java
* Description:
* Author: 임하람
* Since: 2021/03/12
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/12 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.domain.MemberVO;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author hansol
 *
 */
public class MemberLogInDao {

	final Logger LOG = Logger.getLogger(MemberLogInDao.class);
	
	public MemberLogInDao() {

	}

	
	//이메일 보내는 메서드
	public int sendEmail(String memberemail, String memberPassword) {
		int flag = 1;
		
		String host = "smtp.naver.com";
		int    port = 465;
		String user = "ilovend_sh@naver.com";		//네이버아이디주소입력해주세요! @naver.com 까지
		String password = "t6k3s21656";				//네이버비밀번호입력해주세요!	
		//Properties STMP정보 설정
		Properties props=new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", true);
		
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.smtp.ssl.trust", host);
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,password);
			}
		});
		
		MimeMessage  message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(memberemail));
			message.setSubject("비밀번호 찾기 안내");
			message.setText(memberemail+"님의 비밀번호는 : "+memberPassword+"입니다.");
			//전송
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("=메일 전송 완료=");
		
		return flag;
	}

	/**
	 * 로그인한 이메일로 멤버 단건 조회하기
	 * @param param
	 * @return MemberVO
	 */
	public MemberVO doSelectOne(DTO param) {
		MemberVO member = null;
		
		MemberVO inVO = (MemberVO) param;
		LOG.debug("0.param: "+inVO);
		LOG.debug("doSelectOne 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(500);
			sb.append("SELECT member_no,                              \n");
			sb.append("	name,                                         \n");
			sb.append("	email,                                        \n");
			sb.append("	pw,                                           \n");
			sb.append("	location,                                     \n");
			sb.append("	intro,                                        \n");
			sb.append("	TO_CHAR(reg_dt,'YYYY-MM-DD HH24:MI:SS') reg_dt\n");
			sb.append("FROM member                                    \n");
			sb.append("WHERE email = ?                            \n");
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			pstmt.setString(1, inVO.getEmail());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				member = new MemberVO();
				
				member.setMember_no(rs.getInt("member_no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPw(rs.getString("pw"));
				member.setLocation(rs.getString("location"));
				member.setIntro(rs.getString("intro"));
				member.setReg_dt(rs.getString("reg_dt"));
			
			}
			
			LOG.debug("member info: " + member);
			connection.commit();
			
			
		} catch(SQLException e) {
			LOG.debug("doSelectOne: "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return member;
		
	}
	
	/**
	 * 멤버 이메일 유무 확인
	 * @param param
	 * @return 1/0
	 */
	public int idCheck(DTO param) {
		int flag = 0;
		
		MemberVO inVO = (MemberVO) param;
		LOG.debug("idCheck()");
		LOG.debug("1.param :"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null; 
		
		try {
			connection = ConnectionMaker.getConnection();
			LOG.debug("2.데이터베이스 커넥션 구함:"+connection);
			StringBuffer sb = new StringBuffer(500);
			
			sb.append(" SELECT COUNT(*) cnt                                     \n");
			sb.append(" FROM member                                             \n");
			sb.append(" WHERE email=?                                       \n");
			
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
		
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3.  PreparedStatement  : "+pstmt);
			
			pstmt.setString(1, inVO.getEmail());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1  ResultSet  : "+rs);
			
			if(rs.next()==true) {
				flag = rs.getInt("cnt");
				
			}

		}catch(SQLException e) {
			LOG.debug("SQLException"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		
		}
		
		return flag;
	}
	/**
	 * 멤버 이메일과 비밀번호 체크
	 * @param param
	 * @return 1/0
	 */
	public int passwordCheck(DTO param) {
		int flag = 0;
		
		MemberVO inVO = (MemberVO) param;
		LOG.debug("idCheck()");
		LOG.debug("1.param :"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null; 
		try {
			connection = ConnectionMaker.getConnection();
			LOG.debug("2.데이터베이스 커넥션 구함:"+connection);
			StringBuffer sb = new StringBuffer(500);
			
			sb.append(" SELECT COUNT(*) cnt                                     \n");
			sb.append(" FROM  member                                             \n");
			sb.append(" WHERE email=?                                       \n");
			sb.append(" AND   pw=?                                       \n");
			
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
		
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3.  PreparedStatement  : "+pstmt);
			
			pstmt.setString(1, inVO.getEmail());
			pstmt.setString(2, inVO.getPw());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1  ResultSet  : "+rs);
			
			if(rs.next()==true) {
				flag = rs.getInt("cnt");
				
			}

		}catch(SQLException e) {
			LOG.debug("SQLException"+e.getMessage());
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		
		}
		
		return flag;
	}
	
	/**
	 * 멤버 탈퇴
	 * 탈퇴 기능을 넣을지 말지 나중에 의논해야봐야할듯 
	 * @param param
	 * @return
	 */
	public int doDelete(DTO param) {
		int flag = 0;
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			connection = ConnectionMaker.getConnection();
			StringBuffer sb = new StringBuffer(50);
			
			sb.append("DELETE FROM member     \n");
			sb.append("WHERE MEMBER_NO =?     \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2.1. 쿼리실행 PreparedStatement : \n "+sb.toString());
			LOG.debug("3. 쿼리실행 PreparedStatement  : "+pstmt);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 실행 flag : "+flag);
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
}
