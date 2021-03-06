/**
* <pre>
* com.sist.feb.member.dao
* Class Name : MemberDao.java
* Description: 
* Author: 김재욱
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
package com.sist.feb.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.follow.domain.FollowBasicVo;
import com.sist.feb.follow.domain.FollowVO;
import com.sist.feb.member.cmn.ConnectionMaker;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.cmn.JDBCUtil;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.cmn.WorkStandard;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.profile.image.domain.ProfileImageVO;
import com.sist.feb.storage.domain.MemberPostVO;
import com.sist.feb.storage.domain.StorageVO;
import com.sist.feb.storage.domain.StoreTwoVO;

public class MemberDao implements WorkStandard{
	
	final Logger LOG = Logger.getLogger(MemberDao.class);
	
	public MemberDao() {
		
	}
	
	public MemberVO doSelectOne(DTO param) {
		MemberVO member = null;
		
		MemberVO inVO = (MemberVO) param;
		LOG.debug("0.param: "+inVO);
		LOG.debug("doSelectOne 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
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
			sb.append("	TO_CHAR(reg_dt,'YYYY-MM-DD') reg_dt           \n");
			sb.append("FROM member                                    \n");
			sb.append("WHERE member_no = ?                            \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			pstmt.setInt(1, inVO.getMember_no());
			
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
			
		} //--finally
		
		return member;
		
		
	} //--doSelectOne()

	public int doUpdate(DTO param) {
		int flag = 0;
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("doUpdate");
		LOG.debug("0.param:" + inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(1000);
			
			sb.append("UPDATE member        \n");
			sb.append("SET                  \n");
			sb.append("name = ?,            \n");
			sb.append("pw = ?,              \n");
			sb.append("location = ?,        \n");
			sb.append("intro = ?            \n");
			sb.append("WHERE member_no = ?  \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getName());
			pstmt.setString(2, inVO.getPw());
			pstmt.setString(3, inVO.getLocation());
			pstmt.setString(4, inVO.getIntro());
			pstmt.setInt(5, inVO.getMember_no());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doUpdate:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}

	public int doCountMyFollowing(DTO param) {
		int count = 0;
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param:" + inVO);
		LOG.debug("doCountMyFollowing");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("SELECT COUNT(*) MY_FOLLOWING_COUNT \n");
			sb.append("FROM FOLLOW                        \n");
			sb.append("WHERE FOLLOWED_NO = ?              \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setInt(1, inVO.getMember_no());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				count = rs.getInt("MY_FOLLOWING_COUNT");
			}
			
			LOG.debug("count: " + count);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCountMyFollowed:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return count;
	}
	
	public int doCountMyFollowed(DTO param) {
		int count = 0;
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param:" + inVO);
		LOG.debug("doCountMyFollowing");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("SELECT COUNT(*) MY_FOLLOWED_COUNT \n");
			sb.append("FROM FOLLOW                        \n");
			sb.append("WHERE FOLLOWING_NO = ?              \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setInt(1, inVO.getMember_no());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				count = rs.getInt("MY_FOLLOWED_COUNT");
			}
			
			LOG.debug("count: " + count);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCountMyFollowed:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return count;
	}
	
	public List<FollowVO> doInquireFollowing(DTO param){
		List<FollowVO> list = new ArrayList<FollowVO>();
		
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquireFollowing 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(2000);
			
			sb.append("SELECT T2.FOLLOW_NO,                             \n");
			sb.append("    T2.FOLLOWING_NO,                             \n");
			sb.append("    T1.NAME,                                     \n");
			sb.append("    T1.LOCATION,                                 \n");
			sb.append("    TO_CHAR(T2.FOLLOW_DT,'YYYY-MM-DD') FOLLOW_DT,\n");
			sb.append("    (                                            \n");
			sb.append("        SELECT COUNT(*)                          \n");
			sb.append("        FROM FOLLOW T3                           \n");
			sb.append("        WHERE T2.FOLLOWING_NO = T3.FOLLOWING_NO  \n");
			sb.append("    )THEIR_FOLLOWED_COUNT                        \n");
			sb.append("FROM MEMBER T1, FOLLOW T2                        \n");
			sb.append("WHERE T1.MEMBER_NO = T2.FOLLOWING_NO             \n");
			sb.append("AND T2.FOLLOWED_NO = ?                           \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				FollowVO follow = new FollowVO();
				
				follow.setFollowNo(rs.getInt("FOLLOW_NO"));
				follow.setFollowingNo(rs.getInt("FOLLOWING_NO"));
				follow.setName(rs.getString("NAME"));
				follow.setLocation(rs.getString("LOCATION"));
				follow.setFollowDt(rs.getString("FOLLOW_DT"));
				follow.setTheirFollowedCount(rs.getInt("THEIR_FOLLOWED_COUNT"));
				
				list.add(follow);
			}
			
			for(FollowVO vo : list) {
				LOG.debug(vo);
			}
			LOG.debug("=================조회건수"+list.size());
			
			connection.commit();
			
		}  catch(SQLException e) {
			LOG.debug("doInquireFollowing: "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return list;
	}
	
	public List<FollowVO> doInquireFollowed(DTO param){
		List<FollowVO> list = new ArrayList<FollowVO>();
		
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquireFollowed 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(2000);
			
			sb.append("SELECT T2.FOLLOW_NO,                              \n");
			sb.append("    T2.FOLLOWED_NO,                               \n");
			sb.append("    T1.NAME,                                      \n");
			sb.append("    T1.LOCATION,                                  \n");
			sb.append("    TO_CHAR(T2.FOLLOW_DT,'YYYY-MM-DD') FOLLOW_DT, \n");
			sb.append("    (                                             \n");
			sb.append("        SELECT COUNT(*)                           \n");
			sb.append("        FROM FOLLOW T3                            \n");
			sb.append("        WHERE T2.FOLLOWED_NO = T3.FOLLOWING_NO    \n");
			sb.append("    )THEIR_FOLLOWED_COUNT                         \n");
			sb.append("FROM MEMBER T1, FOLLOW T2                         \n");
			sb.append("WHERE T1.MEMBER_NO = T2.FOLLOWED_NO               \n");
			sb.append("AND T2.FOLLOWING_NO = ?                           \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				FollowVO follow = new FollowVO();
				
				follow.setFollowNo(rs.getInt("FOLLOW_NO"));
				follow.setFollowingNo(rs.getInt("FOLLOWED_NO"));
				follow.setName(rs.getString("NAME"));
				follow.setLocation(rs.getString("LOCATION"));
				follow.setFollowDt(rs.getString("FOLLOW_DT"));
				follow.setTheirFollowedCount(rs.getInt("THEIR_FOLLOWED_COUNT"));
				
				list.add(follow);
			}
			
			for(FollowVO vo : list) {
				LOG.debug(vo);
			}
			LOG.debug("=================조회건수"+list.size());
			
			connection.commit();
			
		}  catch(SQLException e) {
			LOG.debug("doInquireFollowed: "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return list;
	}
	
	public List<MemberPostVO> doInquirePost(DTO param){
		List<MemberPostVO> list = new ArrayList<MemberPostVO>();
		
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquirePost 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("SELECT t1.post_no,                                          \n");
			sb.append("    t1.pic_no,                                              \n");
			sb.append("    t1.member_no,                                           \n");
			sb.append("    t2.path || '/' ||t2.save_nm path \n");
			sb.append("FROM post t1, picture t2                                    \n");
			sb.append("WHERE member_no = ?                                         \n");
			sb.append("AND t1.pic_no = t2.pic_no                                   \n");
			sb.append("ORDER BY reg_dt DESC                                        \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				MemberPostVO post = new MemberPostVO();
				
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPicNo(rs.getInt("PIC_NO"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
//				post.setPath(rs.getString("PATH"));
				
				list.add(post);
				
				String realPath = StringUtil.changePathForPost(rs.getString("PATH"));
				post.setPath(realPath);
				
				LOG.debug("realPath:"+realPath);
			}
			
			for(MemberPostVO vo : list) {
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
	
	public List<StoreTwoVO> doInquireStorageSave(DTO param){
		List<StoreTwoVO> list = new ArrayList<StoreTwoVO>();
		
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquireStorageSave 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(2000);
			
			sb.append("SELECT t1.storage_no,                                           \n");
			sb.append("    t1.post_no,                                                 \n");
			sb.append("    t2.member_no AS uploder,                                    \n");
			sb.append("    t1.member_no AS watcher,                                    \n");
			sb.append("    t2.pic_no,                                                  \n");
			sb.append("    t3.path || '/' ||t3.save_nm AS path  \n");
			sb.append("FROM storage t1, post t2, picture t3                            \n");
			sb.append("WHERE t1.post_no = t2.post_no                                   \n");
			sb.append("AND t2.pic_no = t3.pic_no                                       \n");
			sb.append("AND t1.member_no = ?                                            \n");
			sb.append("AND t1.store_type = 1                                           \n");
			sb.append("ORDER BY t1.reg_dt DESC                                         \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				StoreTwoVO save = new StoreTwoVO();
				
//				save.setPostNo(rs.getInt("POST_NO"));
//				save.setPath(rs.getString("PATH"));

				save.setStorageNo(rs.getInt("storage_no"));
				save.setPostNo(rs.getInt("post_no"));
				save.setUploder(rs.getInt("uploder"));
				save.setWatcher(rs.getInt("watcher"));
				save.setPicNo(rs.getInt("pic_no"));
//				save.setPath(rs.getString("path"));
				
				String realPath = StringUtil.changePathForPost(rs.getString("path"));
				save.setPath(realPath);
				
				list.add(save);
			}
			
			for(StoreTwoVO vo : list) {
				LOG.debug(vo);
			}
			LOG.debug("=================조회건수"+list.size());
			
			connection.commit();
			
		}  catch(SQLException e) {
			LOG.debug("doInquireStorageSave: "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return list;
	}
	
	public List<StoreTwoVO> doInquireStorageLike(DTO param){
		List<StoreTwoVO> list = new ArrayList<StoreTwoVO>();
		
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquireStorageLike 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(2000);
			
			sb.append("SELECT t1.storage_no,                                           \n");
			sb.append("    t1.post_no,                                                 \n");
			sb.append("    t2.member_no AS uploder,                                    \n");
			sb.append("    t1.member_no AS watcher,                                    \n");
			sb.append("    t2.pic_no,                                                  \n");
			sb.append("    t3.path || '/' ||t3.save_nm AS path  \n");
			sb.append("FROM storage t1, post t2, picture t3                            \n");
			sb.append("WHERE t1.post_no = t2.post_no                                   \n");
			sb.append("AND t2.pic_no = t3.pic_no                                       \n");
			sb.append("AND t1.member_no = ?                                            \n");
			sb.append("AND t1.store_type = 2                                           \n");
			sb.append("ORDER BY t1.reg_dt DESC                                         \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				StoreTwoVO save = new StoreTwoVO();
				
				save.setStorageNo(rs.getInt("storage_no"));
				save.setPostNo(rs.getInt("post_no"));
				save.setUploder(rs.getInt("uploder"));
				save.setWatcher(rs.getInt("watcher"));
				save.setPicNo(rs.getInt("pic_no"));
//				save.setPath(rs.getString("path"));
				
				String realPath = StringUtil.changePathForPost(rs.getString("path"));
				save.setPath(realPath);
				
				list.add(save);
			}
			
			for(StoreTwoVO vo : list) {
				LOG.debug(vo);
			}
			LOG.debug("=================조회건수"+list.size());
			
			connection.commit();
			
		}  catch(SQLException e) {
			LOG.debug("doInquireStorageLike: "+e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
		}
		
		return list;
	}
	
	public int doUnfollow(DTO param) {
		int flag = 0;
		FollowVO inVO = (FollowVO) param;
		
		LOG.debug("doUnfollow");
		LOG.debug("0.param:" + inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("DELETE              \n");
			sb.append("FROM follow         \n");
			sb.append("WHERE follow_no = ? \n");
			
			LOG.debug(sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO.getFollowNo());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doUnfollow:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
	public int doFollow(DTO param1, DTO param2) {
		int flag = 0;
		FollowVO inVO1 = (FollowVO) param1;
		MemberVO inVO2 = (MemberVO) param2;
		
		LOG.debug("doUnfollow");
		LOG.debug("0.param1:" + inVO1);
		LOG.debug("0.param2:" + inVO2);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("INSERT INTO FOLLOW   \n");
			sb.append("VALUES(              \n");
			sb.append("	FOLLOW_SEQ.nextval, \n");
			sb.append("	?,                  \n");
			sb.append("	?,                  \n");
			sb.append("	SYSDATE             \n");
			sb.append(")                    \n");
			
			LOG.debug(sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO1.getFollowingNo());
			pstmt.setInt(2, inVO2.getMember_no());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doFollow:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
	public List<MemberPostVO> doInquireTheirPost(DTO param){
		List<MemberPostVO> list = new ArrayList<MemberPostVO>();
		
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquirePost 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("SELECT *                                                     \n");
			sb.append("FROM(                                                        \n");
			sb.append("SELECT T1.POST_NO,                                           \n");
			sb.append("    T1.PIC_NO,                                               \n");
			sb.append("    T1.MEMBER_NO,                                            \n");
			sb.append("    T2.PATH || '/' ||T2.SAVE_NM PATH  \n");
			sb.append("FROM POST T1, PICTURE T2                                     \n");
			sb.append("WHERE MEMBER_NO = ?                                          \n");
			sb.append("AND T1.PIC_NO = T2.PIC_NO                                    \n");
			sb.append("ORDER BY REG_DT DESC                                         \n");
			sb.append(")                                                            \n");
			sb.append("WHERE ROWNUM <= 3                                            \n");
			
			LOG.debug(sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			while(rs.next() == true) {
				
				MemberPostVO post = new MemberPostVO();
				
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPicNo(rs.getInt("PIC_NO"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
//				post.setPath(rs.getString("PATH"));
				
				String realPath = StringUtil.changePathForPost(rs.getString("PATH"));
				post.setPath(realPath);
				
				list.add(post);
			}
			
			for(MemberPostVO vo : list) {
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
	
	public int doCheckFollowing(DTO param1, DTO param2) {
		int count = 0;
		FollowVO inVO1 = (FollowVO) param1;
		MemberVO inVO2 = (MemberVO) param2;
		
		LOG.debug("0.param1:" + param1);
		LOG.debug("0.param2:" + param2);
		LOG.debug("doCheckFollowing");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("SELECT COUNT(*) count   \n");
			sb.append("FROM follow             \n");
			sb.append("WHERE FOLLOWING_NO = ?  \n");
			sb.append("AND FOLLOWED_NO = ?     \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setInt(1, inVO1.getFollowingNo());
			pstmt.setInt(2, inVO2.getMember_no());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				count = rs.getInt("count");
			}
			
			LOG.debug("count: " + count);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCountMyFollowed:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return count;
	}
	
	//rs = pstmt.executeQuery();
	public int doCheckProfileImage(DTO param) {
		int flag = 0;
		
		MemberVO inVO = (MemberVO) param;
		LOG.debug("0.param: "+inVO);
		LOG.debug("doCheckProfileImage 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(500);
			sb.append("SELECT file_id,        \n");
			sb.append("	original_file_name,   \n");
			sb.append("	save_file_name,       \n");
			sb.append("	path,                 \n");
			sb.append("	file_size,            \n");
			sb.append("	ext,                  \n");
			sb.append("	registered_date,      \n");
			sb.append("	member_no             \n");
			sb.append("FROM profile_image     \n");
			sb.append("WHERE member_no = ?    \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				flag++;
			}
			
			LOG.debug("flag: " + flag);
			connection.commit();
			
			
		} catch(SQLException e) {
			LOG.debug("doCheckProfileImage: "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		} //--finally
		
		return flag;
		
	} //--doCheckProfileImage()
	
	public int doDeleteProfileImage(DTO param) {
		int flag = 0;
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("doDeleteProfileImage");
		LOG.debug("0.param:" + inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append("DELETE                       \n");
			sb.append("FROM profile_image           \n");
			sb.append("WHERE member_no = ?          \n");
			
			LOG.debug(sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO.getMember_no());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doDeleteProfileImage:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
	public int doRegisterProfileImage(DTO param) {
		int flag = 0;
		ProfileImageVO inVO = (ProfileImageVO) param;
		
		LOG.debug("doRegisterProfileImage");
		LOG.debug("0.param:" + inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(1000);
			
			sb.append("INSERT INTO profile_image (  \n");
			sb.append("    file_id,                 \n");
			sb.append("    original_file_name,      \n");
			sb.append("    save_file_name,          \n");
			sb.append("    path,                    \n");
			sb.append("    file_size,               \n");
			sb.append("    ext,                     \n");
			sb.append("    registered_date,         \n");
			sb.append("    member_no                \n");
			sb.append(") VALUES (                   \n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    ?                        \n");
			sb.append(")                            \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
//			pstmt.setString(1, inVO.getName());
//			pstmt.setInt(5, inVO.getMember_no());
			pstmt.setString(1, inVO.getFileId());
			pstmt.setString(2, inVO.getOrgFileNm());
			pstmt.setString(3, inVO.getSaveFileNm());
			pstmt.setString(4, inVO.getPath());
			pstmt.setLong  (5, inVO.getSize());
			pstmt.setString(6, inVO.getExt());
			pstmt.setString(7, inVO.getRegDt());
			pstmt.setInt(8, inVO.getMemberNo());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doRegisterProfileImage:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
	public ProfileImageVO doInquireProfileImage(DTO param) {
		
		ProfileImageVO outVO = null;
		MemberVO inVO = (MemberVO) param;
		
		LOG.debug("0.param: "+inVO);
		LOG.debug("doInquireProfileImage 시작");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		//Return Value 처리
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			LOG.debug("2.데이터베이스 커넥션 구함 "+connection);
			
			StringBuffer sb = new StringBuffer(500);
			sb.append("SELECT file_id,        \n");
			sb.append("	original_file_name,   \n");
			sb.append("	save_file_name,       \n");
			sb.append("	path,                 \n");
			sb.append("	file_size,            \n");
			sb.append("	ext,                  \n");
			sb.append("	registered_date,      \n");
			sb.append("	member_no             \n");
			sb.append("FROM profile_image     \n");
			sb.append("WHERE member_no = ?    \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("3. PreparedStatement: " + connection);
			pstmt.setInt(1, inVO.getMember_no());
			
			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				
				outVO = new ProfileImageVO();
				
				outVO.setFileId(rs.getString("file_id"));
				outVO.setOrgFileNm(rs.getString("original_file_name"));
				outVO.setSaveFileNm(rs.getString("save_file_name"));
				outVO.setPath(rs.getString("path"));
				outVO.setSize(rs.getLong("file_size"));
				outVO.setExt(rs.getString("ext"));
				outVO.setRegDt(rs.getString("registered_date"));
				outVO.setMemberNo(rs.getInt("member_no"));
				
			}
			
			LOG.debug("outVO: "+outVO);
			
			connection.commit();
			
			
		} catch(SQLException e) {
			LOG.debug("doCheckProfileImage: "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		} //--finally
		
		return outVO;
		
	} //--doCheckProfileImage()
	
	public FollowBasicVo doReturnFollowDetail(DTO param1, DTO param2) {
		FollowBasicVo outVO = null;
		MemberVO inVO1 = (MemberVO) param1;
		MemberVO inVO2 = (MemberVO) param2;
		
		LOG.debug("0.param1:" + param1);
		LOG.debug("0.param2:" + param2);
		LOG.debug("doReturnFollowDetail");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append(" SELECT                    \n");
			sb.append("     follow_no,            \n");
			sb.append("     following_no,         \n");
			sb.append("     followed_no,          \n");
			sb.append("     follow_dt             \n");
			sb.append(" FROM follow               \n");
			sb.append(" WHERE following_no = ?    \n");
			sb.append(" AND followed_no = ?       \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setInt(1, inVO1.getMember_no());
			pstmt.setInt(2, inVO2.getMember_no());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				outVO = new FollowBasicVo();
				outVO.setFollow_no(rs.getInt("follow_no"));
				outVO.setFollowing_no(rs.getInt("following_no"));
				outVO.setFollowed_no(rs.getInt("followed_no"));
				outVO.setFollow_dt(rs.getString("follow_dt"));
			}
			
			LOG.debug("outVO: " + outVO);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCountMyFollowed:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return outVO;
	}
	
	public int doSaveOrLikePost(DTO param) {
		int flag = 0;
		StorageVO inVO = (StorageVO) param;
		
		LOG.debug("doSaveOrLikePost");
		LOG.debug("0.param:" + inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(500);
			
			sb.append(" INSERT INTO storage (            \n");
			sb.append("     storage_no,                  \n");
			sb.append("     store_type,                  \n");
			sb.append("     reg_dt,                      \n");
			sb.append("     post_no,                     \n");
			sb.append("     member_no,                   \n");
			sb.append("     pic_group                    \n");
			sb.append(" ) VALUES (                       \n");
			sb.append("     STORAGE_SEQ.nextval,         \n");
			sb.append("     ?,                           \n");
			sb.append("     SYSDATE,                     \n");
			sb.append("     ?,                           \n");
			sb.append("     ?,                           \n");
			sb.append("     ?                            \n");
			sb.append(" )                                \n");
			
			LOG.debug(sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO.getStore_type());
			pstmt.setInt(2, inVO.getPost_no());
			pstmt.setInt(3, inVO.getMember_no());
			pstmt.setInt(4, inVO.getPic_group());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doSaveOrLikePost:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
	public int doCanselSaveOrLikePost(DTO param) {
		int flag = 0;
		StorageVO inVO = (StorageVO) param;
		
		LOG.debug("doCanselSaveOrLikePost");
		LOG.debug("0.param:" + inVO);
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(200);
			
			sb.append(" DELETE FROM storage            \n");
			sb.append(" WHERE post_no = ?              \n");
			sb.append(" AND store_type = ?             \n");
			sb.append(" AND member_no = ?             \n");
			
			LOG.debug(sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setInt(1, inVO.getPost_no());
			pstmt.setInt(2, inVO.getStore_type());
			pstmt.setInt(3, inVO.getMember_no());
			
			flag = pstmt.executeUpdate();
			LOG.debug("4. 쿼리 수행: " + flag);
			
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCanselSaveOrLikePost:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return flag;
	}
	
	
	public int doCheckSave(PostPicVO param1, MemberVO param2) {
		int count = 0;
		PostPicVO inVO1 = param1;
		MemberVO inVO2 = param2;
		
		LOG.debug("0.param1:" + param1);
		LOG.debug("0.param2:" + param2);
		LOG.debug("doCheckSave");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(200);
			
			sb.append(" SELECT COUNT(*) count         \n");
			sb.append(" FROM storage                  \n");
			sb.append(" WHERE post_no = ?             \n");
			sb.append(" AND member_no = ?             \n");
			sb.append(" AND store_type = 1            \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setInt(1, inVO1.getPostNo());
			pstmt.setInt(2, inVO2.getMember_no());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				count = rs.getInt("count");
			}
			
			LOG.debug("count: " + count);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCheckSave:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return count;
	}
	
	public int doCheckLike(PostPicVO param1, MemberVO param2) {
		int count = 0;
		PostPicVO inVO1 = param1;
		MemberVO inVO2 = param2;
		
		LOG.debug("0.param1:" + param1);
		LOG.debug("0.param2:" + param2);
		LOG.debug("doCheckLike");
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionMaker.getConnection();
			connection.setAutoCommit(false);
			
			StringBuffer sb = new StringBuffer(200);
			
			sb.append(" SELECT COUNT(*) count         \n");
			sb.append(" FROM storage                  \n");
			sb.append(" WHERE post_no = ?             \n");
			sb.append(" AND member_no = ?             \n");
			sb.append(" AND store_type = 2            \n");
			
			LOG.debug("2.1. 쿼리 실행 PreparedStatement: \n" + sb.toString());
			pstmt = connection.prepareStatement(sb.toString());

			pstmt.setInt(1, inVO1.getPostNo());
			pstmt.setInt(2, inVO2.getMember_no());

			rs = pstmt.executeQuery();
			LOG.debug("3.1 ResultSet: " + rs);
			
			if(rs.next() == true) {
				count = rs.getInt("count");
			}
			
			LOG.debug("count: " + count);
			connection.commit();
			
		} catch(SQLException e) {
			LOG.debug("doCheckLike:  "+e.getMessage());
			e.printStackTrace();
		} finally {
			
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(connection);
			
		}
		
		return count;
	}
	
	
}
