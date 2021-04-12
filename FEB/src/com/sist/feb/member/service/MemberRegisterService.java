/**
* <pre>
* com.sist.feb.member.service
* Class Name : MemberRegisterService.java
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
package com.sist.feb.member.service;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.dao.MemberRegisterDao;

/**
 * @author hansol
 *
 */
public class MemberRegisterService {

	private final Logger LOG = Logger.getLogger(MemberRegisterService.class);
	
	private MemberRegisterDao rgDao;
	
	public MemberRegisterService() {
		rgDao = new MemberRegisterDao();
	}
	
	public int dupleIdCheck(DTO param) {
		return rgDao.dupleIdCheck(param);
	}
	
	public int doRegister(DTO param) {
		return rgDao.doRegister(param);
	}

}
