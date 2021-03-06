/**
* <pre>
* com.sist.feb.member.service
* Class Name : MemberLogInService.java
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
package com.sist.feb.member.service;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.dao.MemberLogInDao;
import com.sist.feb.member.domain.MemberVO;

/**
 * @author hansol
 *
 */
public class MemberLogInService {

	private final Logger LOG = Logger.getLogger(MemberLogInService.class);
	
	private MemberLogInDao dao;
	   
	public MemberLogInService() {
		dao = new MemberLogInDao();
	}
	
	public int sendEmail(String memberemail, String memberPassword) {
		int flag = dao.sendEmail(memberemail, memberPassword);
		return flag;
	}
	
	public MemberVO doSelectOne(DTO param) {
		
		LOG.debug("--------------------------");
		LOG.debug("MemberService-doSelectOne");
	
		MemberVO outVO = dao.doSelectOne(param);
		
		return outVO;
		
	}
	
	public int doLoginCheck(DTO dto) {
		int flag = 0;
		
		//MessageVO message = new MessageVO();
		MemberVO param = (MemberVO) dto;
		LOG.debug("param:"+param);
		
		int idCheckFlag = dao.idCheck(param);
		
		if( 1 != idCheckFlag ) {	//아이디가 없음 
			return flag;
		}else {
			int passwordCheckFlag = dao.passwordCheck(param);
			
			if( 1 != passwordCheckFlag) {	//비밀번호가 안맞음
				flag = -1;
				return flag;
			}else {		//아이디 비번 확인됨 
				flag = 1;
				return flag;
			}
			
		}


	}
}
