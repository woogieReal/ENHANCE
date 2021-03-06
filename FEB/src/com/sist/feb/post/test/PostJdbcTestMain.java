/**
* <pre>
* com.sist.feb.post.test
* Class Name : PostJdbcTestMain.java
* Description: PostDao 메서드 단위테스트
* Author: 
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
package com.sist.feb.post.test;

import org.apache.log4j.Logger;

import com.sist.feb.post.dao.PostDao;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.post.domain.PostVO;

/**
 * @author khy81
 *
 */
public class PostJdbcTestMain {
	
	final static Logger LOG = Logger.getLogger(PostJdbcTestMain.class);
	
	//PostVO 멤버변수
	private PostVO post01;
	private PostVO post02;
	private PostVO post03;
	
	private PostDao postDao;
	
	public PostJdbcTestMain() {
		/*public PostVO(int postNo, int memberNo, String title, String contents, int readCnt, String regDt, String modDt,
				int picNo, int picGroup, int categorySeq, int tagNo, int tagGroup) */

		
		post01 = new PostVO(83, 8, "title_888", "contents_888", 0, "", "", 8, 8, 8, 8);
		post02 = new PostVO(84, 88, "title_888", "contents_888", 0, "", "",88, 88,88, 88);
		post03 = new PostVO(888, 888, "title_888", "contents_888", 0, "", "", 88, 88, 88, 88);
		
	
		//Dao객체 생성해서 테스트
		postDao= new PostDao();
	}

	
	public static void main(String[] args) {
		
		PostJdbcTestMain jtMain = new PostJdbcTestMain();
		
//		jtMain.doInsert();
		//jtMain.doDelete();
		//jtMain.doSelectOne();
		//jtMain.doUpdate();
//		jtMain.doInquireCurrval();
		
	}//--main
	
	public void doUpdate() {
		int flag = 0;
		
		PostVO upData = new PostVO();
		upData.setPostNo(post02.getPostNo());
		upData.setTitle(post02.getTitle()+"_U");
		upData.setContents(post02.getContents()+"_U");
		//member_no은 FK라서 수정 불가
		
		flag = postDao.doUpdate(upData);
		
		if(1==flag) {
			LOG.debug("*****************************************");
			LOG.debug("*                 수정 성공                                     *");
			LOG.debug("*****************************************");
		}else {
			LOG.debug("-----------------------------------------");
			LOG.debug("|                 수정 실패                                     |");
			LOG.debug("-----------------------------------------");
		}
	}//--doUpdate()
	
	public void doSelectOne() {
		PostPicVO post = null;
		post = postDao.doSelectOne(post02);
		
		if(post.getPostNo() != post02.getPostNo()
			|| !post.getTitle().equals(post02.getTitle())
			|| !post.getContents().equals(post02.getContents())
				) {
			LOG.debug("-----------------------------------------");
			LOG.debug("|                 조회 실패                                     |");
			LOG.debug("-----------------------------------------");
		}else {
			LOG.debug("*****************************************");
			LOG.debug("*                 조회 성공                                     *");
			LOG.debug("*****************************************");
		}
	}
	
	public void doDelete() {
		int flag =0;
		
		flag = postDao.doDelete(post01);
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
		flag = postDao.doInsert(post03);
		
		if(1==flag) {
			LOG.debug("*****************************************");
			LOG.debug("*                 등록 성공                                     *");
			LOG.debug("*****************************************");
		}else {
			LOG.debug("-----------------------------------------");
			LOG.debug("|                 등록 실패                                     |");
			LOG.debug("-----------------------------------------");
		}
	}
	
	public void doInquireCurrval() {
		int result = 0;
		result = postDao.doInquireCurrval();
		LOG.debug("==============================");
		LOG.debug("result: "+result);
		LOG.debug("==============================");
		
	}
	
	

}//--class
