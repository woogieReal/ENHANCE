/**
* <pre>
* com.sist.feb.reply.dao
* Class Name : ReplyDaoTest.java
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

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.reply.domain.ReplyMemberVO;
import com.sist.feb.reply.domain.ReplyVO;

/**
 * @author hansol
 *
 */


public class ReplyDaoTest {

	final static Logger LOG = Logger.getLogger(ReplyDaoTest.class);
			
	private ReplyVO reply01;
	private ReplyDao replyDao;
	
	public ReplyDaoTest() {
		reply01 = new ReplyVO(41, 22, "댓글내용", "", "", 2);
		replyDao = new ReplyDao();
	}
	
	public static void main(String[] args) {
		
		ReplyDaoTest rtMain = new ReplyDaoTest();
		//rtMain.doInsert();
		//rtMain.doDelete();
		rtMain.checkReply();
		//rtMain.doUpdate();

		
	}
	

	
	public void doUpdate() {
		int flag = 0;

		ReplyVO upData = new ReplyVO();
		
		upData.setReply_no(reply01.getReply_no());
		upData.setContents(reply01.getContents()+"수정");


		flag = replyDao.doUpdate(upData);
		
		if(1==flag) {
			LOG.debug("★★★★★★★★★★★★");
			LOG.debug("수정 성공");
			LOG.debug("★★★★★★★★★★★★");
		}else {
			LOG.debug("---------------");
			LOG.debug("수정 실패"); 
			LOG.debug("---------------");
		}
		
	
	}
	
	public void  checkReply( ) {
	
		List<ReplyMemberVO> resultList = (List<ReplyMemberVO>)replyDao.checkReply(reply01);
		LOG.debug("댓글수:"+resultList.size());
	}
	
	public void doDelete() {
		int flag = 0;
		flag = replyDao.doDelete(reply01);
		//-------------------
		if(1==flag) {
			LOG.debug("★★★★★★★★★★★★");
			LOG.debug("삭제 성공");
			LOG.debug("★★★★★★★★★★★★");
		}else {
			LOG.debug("---------------");
			LOG.debug("삭제 실패"); 
			LOG.debug("---------------");
		}
	
	}
	
	public void doInsert() {
		int flag = 0;
		flag = replyDao.doInsert(reply01);
		//-------------------
		if(1==flag) {
			LOG.debug("★★★★★★★★★★★★");
			LOG.debug("등록 성공");
			LOG.debug("★★★★★★★★★★★★");
		}else {
			LOG.debug("---------------");
			LOG.debug("등록 실패"); 
			LOG.debug("---------------");
		}
	}

}
