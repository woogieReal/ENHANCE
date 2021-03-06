/**
* <pre>
* com.sist.feb.member.test
* Class Name : MemberJdbcTestMain.java
* Description: 테스트
* Author: 김재욱
* Since: 2021/03/05
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/05 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.follow.domain.FollowBasicVo;
import com.sist.feb.follow.domain.FollowVO;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.dao.MemberDao;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.profile.image.domain.ProfileImageVO;
import com.sist.feb.storage.domain.MemberPostVO;
import com.sist.feb.storage.domain.StorageVO;
import com.sist.feb.storage.domain.StoreTwoVO;

public class MemberJdbcTestMain {

	final static Logger LOG = Logger.getLogger(MemberJdbcTestMain.class);
	
	//단건조회용 MemberVO
	private MemberVO memDoSelectOne;
	
	//모든 데이터가 들어가 있는 MemberVO
	@SuppressWarnings("unused")
	private MemberVO member01;
	
	private MemberDao memberDao;
	
	//follow 삭제용
	private FollowVO follow;
	
	//프로필 이미지용
	private ProfileImageVO image;
	
	//저장/좋아요 용
	private StorageVO store;
	
	
	public MemberJdbcTestMain() {
		
		//단건조회용 MemberVO
		memDoSelectOne = new MemberVO(2, "", "", "", "", "", "");
		
		//모든 데이터가 들어가 있는 MemberVO
		member01 = new MemberVO( 1, "Mark", "mark@gamil.com","mark123","america","I'm Mark!","21/03/04");
		
		memberDao = new MemberDao();
		
		//follow
		follow = new FollowVO(0, 33, "", "", "", 1);
		
		//프로필 이미지용
		image = new ProfileImageVO("qg13g113g36231", "aa", "markaa", "/picture/2021/03/", 426223, "jpg", "21/03/15", 1);
		
		//저장/좋아요 용
		store = new StorageVO(0, 2, "", 249, 1, 249);
//		store = new StorageVO(0, 1, "", 252, 1, 253);
		
	}
	
	
	public static void main(String[] args) {
		
		MemberJdbcTestMain jtMain = new MemberJdbcTestMain();
		
//		jtMain.doSelectOne();
//		jtMain.doUpdate();
//		jtMain.doCountMyFollowing();
//		jtMain.doCountMyFollowed();
//		jtMain.doInquireFollowing();
//		jtMain.doInquireFollowed();
//		jtMain.doInquirePost();
//		jtMain.doInquireStorageSave();
//		jtMain.doInquireStorageLike();
//		jtMain.doUnfollow();
//		jtMain.doFollow();
//		jtMain.doInquireTheirPost();
//		jtMain.doCheckFollowing();
//		jtMain.doDeleteProfileImage();
//		jtMain.doRegisterProfileImage();
//		jtMain.doCheckProfileImage();
//		jtMain.doInquireProfileImage();
//		jtMain.doReturnFollowDetail();
		jtMain.doSaveOrLikePost();
//		jtMain.doCanselSaveOrLikePost();
		
	}
	
	public void doSelectOne() {
		MemberVO member = null;
		member = memberDao.doSelectOne(memDoSelectOne);
		
		if(member.getMember_no() != memDoSelectOne.getMember_no()) {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug("실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}else {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug("단건조회 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}
		
	}//--doSelectOne()
	
	public void doUpdate() {
		int flag = 0;
		
		MemberVO updata = new MemberVO();
		
		updata.setName(member01.getName());
		updata.setLocation(member01.getLocation());
		updata.setIntro(member01.getIntro());
		updata.setMember_no(member01.getMember_no());
		
		flag = memberDao.doUpdate(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 수정 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 수정 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
		
	}
	
	public void doCountMyFollowing() {
		
		int result = memberDao.doCountMyFollowing(memDoSelectOne);
		LOG.debug("result: "+result);
		
	}
	
	public void doCountMyFollowed() {
		
		int result = memberDao.doCountMyFollowed(memDoSelectOne);
		LOG.debug("result: "+result);
		
	}
	
	public void doInquireFollowing() {
		
		List<FollowVO> resultList = (List<FollowVO>)memberDao.doInquireFollowing(memDoSelectOne);
		LOG.debug("doInquireFollowing() 건수: "+resultList.size());

		
	}
	
	public void doInquireFollowed() {
		
		List<FollowVO> resultList = (List<FollowVO>)memberDao.doInquireFollowed(memDoSelectOne);
		LOG.debug("doInquireFollowed() 건수: "+resultList.size());

		
	}
	
	public void doInquirePost() {
		List<MemberPostVO> resultList = (List<MemberPostVO>)memberDao.doInquirePost(memDoSelectOne);
		LOG.debug("doInquirePost() 건수: "+resultList.size());
	}
	
	public void doInquireStorageSave() {
		List<StoreTwoVO> resultList = (List<StoreTwoVO>)memberDao.doInquireStorageSave(memDoSelectOne);
		LOG.debug("doInquireStorageSave() 건수: "+resultList.size());
	}
	
	public void doInquireStorageLike() {
		List<StoreTwoVO> resultList = (List<StoreTwoVO>)memberDao.doInquireStorageLike(memDoSelectOne);
		LOG.debug("doInquireStorageLike() 건수: "+resultList.size());
	}
	
	public void doUnfollow() {
		int flag = 0;
		
		FollowVO updata = new FollowVO();
		
		updata.setFollowNo(follow.getFollowNo());
		
		flag = memberDao.doUnfollow(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 언팔 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 언팔 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	public void doFollow() {
		int flag = 0;
		
		FollowVO updata1 = new FollowVO();
		MemberVO updata2 = new MemberVO();
		
		updata1.setFollowingNo(follow.getFollowingNo());
		updata2.setMember_no(memDoSelectOne.getMember_no());
		
		flag = memberDao.doFollow(updata1, updata2);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 팔로우 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 팔로우 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	public void doInquireTheirPost() {
		List<MemberPostVO> resultList = (List<MemberPostVO>)memberDao.doInquireTheirPost(memDoSelectOne);
		LOG.debug("doInquireTheirPost() 건수: "+resultList.size());
	}
	
	public void doCheckFollowing() {
		int flag = 0;
		
		FollowVO updata1 = new FollowVO();
		MemberVO updata2 = new MemberVO();
		
		updata1.setFollowingNo(follow.getFollowingNo());
		updata2.setMember_no(memDoSelectOne.getMember_no());
		
		flag = memberDao.doCheckFollowing(updata1, updata2);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 맞팔 중");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 맞팔 아님");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	public void doDeleteProfileImage() {
		int flag = 0;
		
		MemberVO updata = new MemberVO();
		
		updata.setMember_no(memDoSelectOne.getMember_no());
		
		flag = memberDao.doDeleteProfileImage(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 프로필 이미지 삭제 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 프로필 이미지 삭제 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	public void doRegisterProfileImage() {
		int flag = 0;
		
		ProfileImageVO updata = new ProfileImageVO();
		
//		updata.setMember_no(memDoSelectOne.getMember_no());
		updata.setFileId(image.getFileId());
		updata.setOrgFileNm(image.getOrgFileNm());
		updata.setSaveFileNm(image.getSaveFileNm());
		updata.setPath(image.getPath());
		updata.setSize(image.getSize());
		updata.setExt(image.getExt());
		updata.setRegDt(image.getRegDt());
		updata.setMemberNo(image.getMemberNo());
		
		flag = memberDao.doRegisterProfileImage(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 프로필 이미지 등록 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 프로필 이미지 등록 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	public void doCheckProfileImage() {
		int flag = 0;
		
		ProfileImageVO updata = new ProfileImageVO();
		updata.setMemberNo(image.getMemberNo());
		
		flag = memberDao.doCheckProfileImage(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 프로필 이미지가 이미 있음");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 프로필 이미지가 없음");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	
	public void doInquireProfileImage() {
		ProfileImageVO outVO = null;
		outVO = memberDao.doInquireProfileImage(memDoSelectOne);
		
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
	
	public void doReturnFollowDetail() {
		FollowBasicVo outVO = null;
		
		outVO = memberDao.doReturnFollowDetail(memDoSelectOne, member01);
		
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
	
	public void doSaveOrLikePost() {
		int flag = 0;
		
		StorageVO updata = new StorageVO();
		
		updata.setStorage_no(store.getStorage_no());
		updata.setStore_type(store.getStore_type());
		updata.setReg_dt(store.getReg_dt());
		updata.setPost_no(store.getPost_no());
		updata.setMember_no(store.getMember_no());
		updata.setPic_group(store.getPic_group());
		LOG.debug("store: "+store);
		
		flag = memberDao.doSaveOrLikePost(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 저장/좋아요 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 저장/좋아요 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	public void doCanselSaveOrLikePost() {
		int flag = 0;
		
		StorageVO updata = new StorageVO();
		
		updata.setStorage_no(store.getStorage_no());
		updata.setStore_type(store.getStore_type());
		updata.setReg_dt(store.getReg_dt());
		updata.setPost_no(store.getPost_no());
		updata.setMember_no(store.getMember_no());
		updata.setPic_group(store.getPic_group());
		LOG.debug("store: "+store);
		
		flag = memberDao.doCanselSaveOrLikePost(updata);
		
		if(1 == flag) {
			LOG.debug("★★★★★★★★★★★★★★★★★★");
			LOG.debug(" 저장/좋아요 삭제 성공");
			LOG.debug("★★★★★★★★★★★★★★★★★★");
		}else {
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
			LOG.debug(" 저장/좋아요  삭제 실패");
			LOG.debug("xxxxxxxxxxxxxxxxxxxxxx");
		}
		
	}
	
	
	
	
	
	
}
