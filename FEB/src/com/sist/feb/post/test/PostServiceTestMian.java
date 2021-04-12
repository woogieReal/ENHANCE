/**
* <pre>
* com.sist.feb.post.test
* Class Name : PostServiceTestMian.java
* Description:
* Author: 
* Since: 2021/03/13
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/13 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.post.test;

import org.apache.log4j.Logger;

import com.sist.feb.post.cmn.PostMsgVO;
import com.sist.feb.post.domain.PostVO;
import com.sist.feb.post.service.PostService;


/**
 * @author khy81
 *
 */
public class PostServiceTestMian {
	
	final static Logger LOG = Logger.getLogger(PostServiceTestMian.class);

	private PostService postService;
	private PostVO post01;
	private PostVO post02;
	
	public PostServiceTestMian() {
		
		postService = new PostService();
		post01 = new PostVO(85, 85, "title_80", "contents_80", 85, "", "", 85, 85, 85, 85);
		post02 = new PostVO();
	}
	
	public void doPost() {
		LOG.debug("====================================");
		LOG.debug("=             doPost               = ");
		LOG.debug("====================================");
		
//		PostMsgVO message = postService.doPost(post01);
//		if(message.getMsgId().equals("0")) {
//			LOG.debug(message.getMsgId()+","+message.getMsgContents());
//		}
		//오류나는데.. 한 메서드에 여러 여러 VO를 
	}

	/**
	 * 메인테스트
	 * @param args
	 */
	public static void main(String[] args) {
		PostServiceTestMian serviceMain = new PostServiceTestMian();
		
		serviceMain.doPost();

	}

}
