/**
* <pre>
* com.sist.feb.reply.service
* Class Name : ReplyService.java
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
import com.sist.feb.reply.cmn.DTO;
import com.sist.feb.reply.dao.ReplyDao;
import com.sist.feb.reply.domain.ReplyMemberVO;
import com.sist.feb.reply.domain.ReplyVO;

/**
 * @author hansol
 *
 */
public class ReplyService {

	private final Logger LOG = Logger.getLogger(ReplyService.class);
	
	private ReplyDao dao;
	
	public ReplyService() {
		dao = new ReplyDao();
		
	}

	
	
	/**
	 * 게시물별 댓글 조회
	 * @param param
	 * @return list<ReplyVO>
	 */
	public List<ReplyMemberVO> checkReply(DTO param) {
		return dao.checkReply(param);
	}
	
	/**
	 * 댓글 내용 수정
	 * @param param
	 * @return 성공 : 1, 실패 : 0
	 */
	public int doUpdate(DTO param) {
		return dao.doUpdate(param);
	}
	
	/**
	 * 댓글 삭제
	 * @param param
	 * @return 성공 : 1, 실패 : 0
	 */
	public int doDelete(DTO param) {
		return dao.doDelete(param);
	}
	
	/**
	 * 댓글 등록
	 * @param param
	 * @return 성공 : 1, 실패 : 0
	 */
	public int doInsert(DTO param) {
		return dao.doInsert(param);
	}
}
