/**
* <pre>
* com.sist.eclass.cmn
* Class Name : ConnectionMaker.java
* Description: Connection을 생성해서 Return
* Author: 123wo
* Since: 2021/03/05
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/05 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


/**
 * @author 123wo
 *
 */
public class ConnectionMaker {
	
	final static Logger LOG = Logger.getLogger(ConnectionMaker.class);
	


	/**
	 * DB Connection 생성
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = null;
		String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
//		String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521/xe";
		String USER_ID = "woogie";
		String USER_PASS = "5609";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			LOG.debug("ClassNotFoundException "+e.getMessage());
			e.printStackTrace();
		}
		LOG.debug("1.JDBC 드라이버 로딩 성공");
		con = DriverManager.getConnection(DB_URL, USER_ID, USER_PASS);

		LOG.debug("2.데이터베이스 커넥션 구함 "+ con);
		return con;
	}
	
}
