/**
* <pre>
* com.sist.feb.member.test
* Class Name : MemberRegisterServiceTest.java
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
package com.sist.feb.member.test;

import org.apache.log4j.Logger;

import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberRegisterService;

/**
 * @author hansol
 *
 */
public class MemberRegisterServiceTest {

	private final Logger LOG = Logger.getLogger(MemberRegisterServiceTest.class);
	private MemberRegisterService service;
	private MemberVO member01;
	
	public MemberRegisterServiceTest() {
		service = new MemberRegisterService();
		member01 = new MemberVO(1, "hansol", "hansol@gmail.com", "hansol123", "Korea", "", ""); 
		
	}
	
	public static void main(String[] args) {
		MemberRegisterServiceTest testMain = new MemberRegisterServiceTest();
		testMain.doRegister();
	}
	
	public void doRegister() {
		LOG.debug("===================");
		LOG.debug("doRegister");
		LOG.debug("===================");
		
		int result = service.doRegister(member01);
		LOG.debug("result: "+result);
		
	}

}
