/**
* <pre>
* com.sist.feb.member.test
* Class Name : MemberRegisterDaoTest.java
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

import com.sist.feb.member.dao.MemberDao;
import com.sist.feb.member.dao.MemberRegisterDao;
import com.sist.feb.member.domain.MemberVO;

/**
 * @author hansol
 *
 */
public class MemberRegisterDaoTest {

	
	final static Logger LOG = Logger.getLogger(MemberRegisterDaoTest.class);
	
	private MemberVO  member01;
	private MemberRegisterDao registerDao;
	
	public MemberRegisterDaoTest() {
		member01 = new MemberVO(1, "hansol", "hansol@gmail.com", "hansol123", "Korea", "", "");
		registerDao = new MemberRegisterDao();
	}
	
	
	public static void main(String[] args) {	
		
		MemberRegisterDaoTest rgDaoTest = new MemberRegisterDaoTest();
		rgDaoTest.doRegister();
		
	}
		
	public void doRegister() {
		int flag = 0;
		flag = registerDao.doRegister(member01);
		
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
