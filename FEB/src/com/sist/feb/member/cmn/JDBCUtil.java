/**
* <pre>
* com.sist.eclass.board.cmn
* Class Name : JDBCUtil.java
* Description: JDBC Util
* Author: 123wo
* Since: 2021/02/18
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/02/18 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.cmn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author 123wo
 *
 */
public class JDBCUtil {
	
	private static final Logger LOG = Logger.getLogger(JDBCUtil.class);
	
	/**
	 * ResultSet 자원반납
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		if(null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.debug("rs.close: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * PreparedStatement 자원반납
	 * @param pstmt
	 */
	public static void close(PreparedStatement pstmt) {
		if(null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LOG.debug("pstmt.close: "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Connection 자원반납
	 * @param connection
	 */
	public static void close(Connection connection) {
		if(null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.debug("connection.close: "+ e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	
}
