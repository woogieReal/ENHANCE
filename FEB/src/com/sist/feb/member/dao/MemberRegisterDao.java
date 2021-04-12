/**
* <pre>
* com.sist.feb.member.dao
* Class Name : MemberRegisterDao.java
* Description:
* Author: 임하람
* Since: 2021/03/09
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/09 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.domain.MemberVO;

/**
 * @author hansol
 *
 */
public class MemberRegisterDao extends DTO{

	final Logger LOG = Logger.getLogger(MemberRegisterDao.class);
	
	public MemberRegisterDao() {
		
		
	}
	
	//이메일 보내는 메서드
	public int sendEmail(String memberemail, String memberPassword) {
		int flag = 1;
		
		String host = "smtp.naver.com";
		int    port = 465;
		String user = "ilovend_sh@naver.com";		//네이버아이디주소입력해주세요! @naver.com 까지
		String password = "t6k3s21676";	//네이버비밀번호입력해주세요!	
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
	 * 이메일 중복 체크 
	 * @param param
	 * @return 중복 :1 / 중복아님 :0
	 */
	public int dupleIdCheck(DTO param) {
		int flag = 0;
		
		MemberVO inVO = (MemberVO) param;
		LOG.debug("dupleIdCheck()");
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
	 * 회원가입
	 * @param param
	 * @return
	 */
	public int doRegister(DTO param) {
		int flag = 0;
		
		MemberVO inVO = (MemberVO)param;
		LOG.debug("0.param:"+inVO);
		
		Connection connection = null;
		PreparedStatement pstmt =null;
		
		try {
			connection = ConnectionMaker.getConnection();
			LOG.debug("2.데이터베이스 커넥션 구함:"+connection);
			
			StringBuffer sb = new StringBuffer(1000);
			sb.append("INSERT INTO MEMBER (");
			sb.append("		member_no,      \n ");
			sb.append("		name,    \n ");
			sb.append("		email, \n ");
			sb.append("		pw,      \n ");
			sb.append("		location    \n ");
			sb.append(")VALUES (      \n ");
			sb.append("		MEMBER_SEQ.nextval,        \n ");
			sb.append("		?,        \n ");
			sb.append("		?,        \n ");
			sb.append("		?,        \n ");
			sb.append("		?         \n ");
			sb.append(")              \n ");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1,inVO.getName());
			pstmt.setString(2,inVO.getEmail());
			pstmt.setString(3,inVO.getPw());
			pstmt.setString(4,inVO.getLocation());
			
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
