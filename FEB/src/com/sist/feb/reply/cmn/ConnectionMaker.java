/*
 * Connection을 생성해서 Return 
 */
package com.sist.feb.reply.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionMaker {
	
	final static Logger LOG= Logger.getLogger(ConnectionMaker.class);

	/**
	 * DB Connection 생성
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = null;
		final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
		//final  String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521:xe";
		String USER_ID = "woogie";
		String USER_PASS = "5609";
		
		try {
			//클래스 로더를 통해 해당 데이터베이스 드라이버를 로드
			//JVM에 해당 클래스의 정보를 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			LOG.debug("ClassNotFoundException : "+e.getMessage());
			e.printStackTrace();
		}
		LOG.debug("1.JDBC 드라이버 로딩 성공");
		
		con = DriverManager.getConnection(DB_URL, USER_ID, USER_PASS);
		LOG.debug("2.데이터베이스 커넥션 구함:"+con);
		return con;
	}
}
