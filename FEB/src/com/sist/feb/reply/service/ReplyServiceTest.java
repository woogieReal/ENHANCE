/**
* <pre>
* com.sist.feb.reply.service
* Class Name : ReplyServiceTest.java
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
package com.sist.feb.reply.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.reply.domain.ReplyMemberVO;
import com.sist.feb.reply.domain.ReplyVO;

/**
 * @author hansol
 *
 */
public class ReplyServiceTest {

	private final Logger LOG = Logger.getLogger(ReplyServiceTest.class);
	private ReplyService service;
	private ReplyVO reply01;
	
	public ReplyServiceTest() {
		service = new ReplyService();
		reply01 = new ReplyVO(24, 22, "댓글내용", "", "", 2); 
	}
	
	public static void main(String[] args) {
		
		ReplyServiceTest testMain = new ReplyServiceTest();
		//testMain.checkReply();
	
	}


	
	public void checkReply() {
		LOG.debug("===================");
		LOG.debug("checkReply");
		LOG.debug("===================");
		
		List<ReplyMemberVO> outVO = service.checkReply(reply01);
		LOG.debug(outVO.toString());
	}
}
