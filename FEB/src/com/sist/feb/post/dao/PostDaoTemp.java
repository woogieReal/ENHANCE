/**
* <pre>
* com.sist.feb.post.dao
* Class Name : PostDao.java
* Description: 임시 파일로 무시하셔도 됩니다...commit을 잘못했어요..ㅠ
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
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.post.cmn.PostDTO;
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

public class PostDaoTemp{
	
	final Logger LOG = Logger.getLogger(PostDaoTemp.class);
	
	// 접속에 필요한 정보로 구성된 문자열(Connecting String)
	final static String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521/xe";
	final static String USER_ID = "feb";
	final static String USER_PASS = "sistfeb";
	
	
	public static void main(String[] args) {
		PostDaoTemp postTemp = new PostDaoTemp();
		postTemp.doInsert();
		//postTemp.doDelete();
	}
	
	
	//Post삭제 메서드
	public int doDelete() {
		int flag =0;
		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = getConnection();
			//오토커밋이 자동으로 작동되지 못하게
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(50);
			//2.쿼리 실행
			sb.append(" DELETE FROM post    \n");
			sb.append(" WHERE post_no= ?   	 	\n");
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, 888);
			
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
	
	//Post작성 메서드
	public int doInsert() {
		int flag = 0;

		
		//DB접속 클래스
		Connection connection = null;
		PreparedStatement pstmt = null;
		//PreparedStatement : binding sql을 쓴다. Statement보다 sql수행 속도가 우수
		
		try {
			//1.jdbc드라이버로딩, DB접속
			connection = getConnection();
			
			//2.쿼리 실행
			StringBuffer sb = new StringBuffer(1000);
			
			sb.append(" INSERT INTO post ( \n");
			sb.append("     post_no,       \n");
			sb.append("     member_no,     \n");
			sb.append("     title,         \n");
			sb.append("     contents,      \n");
			sb.append("     pic_no,        \n");
			sb.append("     pic_group      \n");
			sb.append(" ) VALUES (         \n");
			sb.append("     ?,             \n");
			sb.append("     ?,             \n");
			sb.append("     ?,             \n");
			sb.append("     ?,             \n");
			sb.append("     ?,             \n");
			sb.append("     ?              \n");
			sb.append(" )                  \n");
			
			//3.쿼리 실행을 위한 객체 생성
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt   (1, 888);
			pstmt.setString(2, "888");
			pstmt.setString(3, "title_888");
			pstmt.setString(4, "contents_888");
			pstmt.setString(5, "888");
			pstmt.setString(6, "888");
			
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
	
	
	/**
	 * Driver 로딩,
	 * 
	 * @return Connection 중복코드를 메소드로 만들어서 사용하기
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException {
		Connection con = null; // 지역변수는메서드안에서만 쓰이는것

		try {
			//클래스 로더를 통해 해당 데이터베이스 드라이버 로드
			//JVM에게 해당 클래스의 정보를 로드한다.
			// 1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // OracleDriver클래스의 위치 : ""안의 내용이 틀리면 예외처리됨(대소문자 구분됨)
		} catch (ClassNotFoundException e) {
			LOG.debug("ClassNotFoundException:" + e.getMessage());
			e.printStackTrace();
		}
		LOG.debug("1.JDBC 드라이버 로딩 성공!");

		// 2. 데이터베이스 커넥션(접속): Connection객체 생성
		con = DriverManager.getConnection(DB_URL, USER_ID, USER_PASS);

		LOG.debug("2. 데이터베이스 커넥션 구함:" + con);
		return con;
	}//--getConnection
	
	
	
}//--class	




