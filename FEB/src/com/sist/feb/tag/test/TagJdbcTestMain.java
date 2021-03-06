/**
* <pre>
* com.sist.feb.tag.test
* Class Name : TagJdbcTestMain.java
* Description:
* Author: 
* Since: 2021/03/10
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/10 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.tag.test;

import org.apache.log4j.Logger;

import com.sist.feb.tag.dao.TagDao;
import com.sist.feb.tag.domain.TagVO;


/**
 * @author khy81
 *
 */
public class TagJdbcTestMain {
	
	final static Logger LOG = Logger.getLogger(TagJdbcTestMain.class);
	
	private TagVO tag01;
	
	private TagDao tagDao;
	
	public TagJdbcTestMain(){
		
		tag01 = new TagVO(0,0,"아무거나");
		
		tagDao = new TagDao();
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TagJdbcTestMain jtMain = new TagJdbcTestMain();
		
		jtMain.doInsert();

	}
	
	
	public void doInsert() {
		
		int flag =0;
		flag = tagDao.doInsert(tag01);
		
		if(1==flag) {
			LOG.debug("*****************************************");
			LOG.debug("*                 등록 성공                                     *");
			LOG.debug("*****************************************");
		}else {
			LOG.debug("-----------------------------------------");
			LOG.debug("|                 등록 실패                                     |");
			LOG.debug("-----------------------------------------");
		}
	}//--doInsert

}
