/**
* <pre>
* com.sist.feb.member.test
* Class Name : MemberServiceTestMain.java
* Description:
* Author: 123wo
* Since: 2021/03/07
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/07 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.follow.domain.FollowBasicVo;
import com.sist.feb.follow.domain.FollowVO;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberService;
import com.sist.feb.profile.image.domain.ProfileImageVO;
import com.sist.feb.storage.domain.MemberPostVO;
import com.sist.feb.storage.domain.StorageVO;
import com.sist.feb.storage.domain.StoreTwoVO;

/**
 * @author 123wo
 *
 */
public class MemberServiceTestMain {

	private final Logger LOG = Logger.getLogger(MemberServiceTestMain.class);
	private MemberService service;
	private static MemberVO member01;
	private static MemberVO member02;
	private static FollowVO follow;
	private static ProfileImageVO image;
	private StorageVO store;
	
	public MemberServiceTestMain() {
		service = new MemberService();
		member01 = new MemberVO(2, "", "", "", "", "", "");
		member02 = new MemberVO(1, "Mark", "mark@gamil.com","mark123","america","I'm Mark!","21/03/04");
		follow = new FollowVO(3, 33, "", "", "", 1);
		image = new ProfileImageVO("qg13g113g36231", "aa", "markaa", "/picture/2021/03/", 426223, "jpg", "21/03/15", 3);
		store = new StorageVO(0, '1', "", 252, 1, 253);
	}
	
	
	public static void main(String[] args) {
		
		MemberServiceTestMain testMain = new MemberServiceTestMain();
//		testMain.doSelectOne(member01);
//		testMain.doUpdate(member02);
//		testMain.doCountMyFollowing(member01);
//		testMain.doCountMyFollowed(member01);
//		testMain.doInquireFollowing(member01);
//		testMain.doInquireFollowed(member01);
//		testMain.doInquirePost(member01);
//		testMain.doInquireStorageSave(member01);
//		testMain.doInquireStorageLike(member01);
//		testMain.doUnfollow(follow);
//		testMain.doFollow(follow, member01);
//		testMain.doCheckFollowing(follow, member01);
//		testMain.doDeleteProfileImage(member01);
//		testMain.doRegisterProfileImage(image);
//		testMain.doCheckProfileImage(image);
//		testMain.doInquireProfileImage(member01);
//		testMain.doReturnFollowDetail(member01, member02);
		
		
	}
	
	public void doSelectOne(DTO param) {
		
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doSelectOne=");
		LOG.debug("================================");
		
		MemberVO outVO = service.doSelectOne(param);
		LOG.debug("outVO: "+outVO.toString());

	}
	
	public void doUpdate(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doUpdate=");
		LOG.debug("================================");
		
		int result = service.doUpdate(param);
		LOG.debug("result: "+result);
	}
	
	public void doCountMyFollowing(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doCountMyFollowing=");
		LOG.debug("================================");
		
		int result = service.doCountMyFollowing(param);
		LOG.debug("result: "+result);
	}
	
	public void doCountMyFollowed(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doCountMyFollowed=");
		LOG.debug("================================");
		
		int result = service.doCountMyFollowed(param);
		LOG.debug("result: "+result);
	}
	
	public void doInquireFollowing(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doInquireFollowing=");
		LOG.debug("================================");
		
		List<FollowVO> outVO = service.doInquireFollowing(param);
		LOG.debug("outVO: "+outVO);
	}
	
	public void doInquireFollowed(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doInquireFollowed=");
		LOG.debug("================================");
		
		List<FollowVO> outVO = service.doInquireFollowed(param);
		LOG.debug("outVO: "+outVO);
	}
	
	public void doInquirePost(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doInquirePost=");
		LOG.debug("================================");
		
		List<MemberPostVO> outVO = service.doInquirePost(param);
		LOG.debug("outVO: "+outVO);
	}
	
	public void doInquireStorageSave(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doInquireStorageSave=");
		LOG.debug("================================");
		
		List<StoreTwoVO> outVO = service.doInquireStorageSave(param);
		LOG.debug("outVO: "+outVO);
	}
	
	public void doInquireStorageLike(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doInquireStorageLike=");
		LOG.debug("================================");
		
		List<StoreTwoVO> outVO = service.doInquireStorageLike(param);
		LOG.debug("outVO: "+outVO);
	}
	
	public void doUnfollow(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doUnfollow=");
		LOG.debug("================================");
		
		int result = service.doUnfollow(param);
		LOG.debug("result: "+result);
	}
	
	public void doFollow(DTO param1, DTO param2) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doFollow=");
		LOG.debug("================================");
		
		int result = service.doFollow(param1, param2);
		LOG.debug("result: "+result);
	}
	
	public void doCheckFollowing(DTO param1, DTO param2) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doCheckFollowing=");
		LOG.debug("================================");
		
		int result = service.doCheckFollowing(param1, param2);
		LOG.debug("result: "+result);
	}
	
	public void doDeleteProfileImage(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doDeleteProfileImage=");
		LOG.debug("================================");
		
		int result = service.doDeleteProfileImage(param);
		LOG.debug("result: "+result);
	}
	
	public void doRegisterProfileImage(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doRegisterProfileImage=");
		LOG.debug("================================");
		
		int result = service.doRegisterProfileImage(param);
		LOG.debug("result: "+result);
	}
	
	public void doCheckProfileImage(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doCheckProfileImage=");
		LOG.debug("================================");
		
		int result = service.doCheckProfileImage(param);
		LOG.debug("result: "+result);
		
		if(1 == result) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 프로필 이미지가 이미 있음");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 프로필 이미지가 없음");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
	}
	
	public void doInquireProfileImage(DTO param) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doInquireProfileImage=");
		LOG.debug("================================");
		
		ProfileImageVO outVO = service.doInquireProfileImage(param);
		LOG.debug("outVO: "+outVO);
		
		if(outVO == null) {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug("실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}else {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug("단건조회 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}
		
	}
	
	public void doReturnFollowDetail(DTO param1, DTO param2) {
		LOG.debug("================================");
		LOG.debug("=MemberServiceTestMain---doReturnFollowDetail=");
		LOG.debug("================================");
		
		FollowBasicVo outVO = null;
		outVO = service.doReturnFollowDetail(param1, param2);
		LOG.debug("outVO: "+outVO);
		
		if(outVO == null) {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug("실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}else {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug("단건조회 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}
		
	}
	
}
