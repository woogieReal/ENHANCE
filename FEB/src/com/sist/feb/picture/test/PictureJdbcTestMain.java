/**
* <pre>
* com.sist.feb.picture.test
* Class Name : PictureJdbcTestMain.java
* Description:
* Author: 
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
package com.sist.feb.picture.test;

import org.apache.log4j.Logger;

import com.sist.feb.picture.dao.PictureDao;
import com.sist.feb.picture.domain.PictureVO;
import com.sist.feb.post.test.PostJdbcTestMain;

/**
 * @author khy81
 *
 */
public class PictureJdbcTestMain {
	
	final static Logger LOG = Logger.getLogger(PictureJdbcTestMain.class);

	//PostVO 멤버변수
	private PictureVO pic01;
	private PictureVO pic02;
	
	private PictureDao picDao;
	
	public PictureJdbcTestMain() {
		
		pic01 = new PictureVO(82,62,"/pictures/group2/adver01.JPG","adver01","adver01a");
		pic02 = new PictureVO(5,1,"/pictures","graphic02","graphic02b");
		picDao = new PictureDao();
	}
		
		
	public static void main(String[] args) {
		
		
		PictureJdbcTestMain jtMain = new PictureJdbcTestMain();
		
		jtMain.doInsert();
		//jtMain.doDelete();
		//jtMain.doSelectOne();
	}
	
	
	
	public void doSelectOne() {
		PictureVO picture = null;
		picture = (PictureVO)picDao.doSelectOne(pic02);
		
		if(picture.getPicNo() != pic02.getPicNo()
			|| !picture.getPath().equals(pic02.getPath())
			|| !picture.getPicNm().equals(pic02.getPicNm())
				) {
			LOG.debug("-----------------------------------------");
			LOG.debug("|                 조회 실패                                     |");
			LOG.debug("-----------------------------------------");
		}else {
			LOG.debug("*****************************************");
			LOG.debug("*                 조회 성공                                     *");
			LOG.debug("*****************************************");
		}
	}//--doSelectOne()
	
	
	public void doDelete() {
		int flag =0;
		flag = picDao.doDelete(pic01);
		if(1==flag) {
			LOG.debug("*****************************************");
			LOG.debug("*                 삭제 성공                                     *");
			LOG.debug("*****************************************");
		}else {
			LOG.debug("-----------------------------------------");
			LOG.debug("|                 삭제 실패                                     |");
			LOG.debug("-----------------------------------------");
		}
	}
	
	public void doInsert() {
		
		int flag =0;
		flag = picDao.doInsert(pic01);
		
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
	

}//--class
