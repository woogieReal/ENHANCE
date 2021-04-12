/**
* <pre>
* com.sist.feb.member.service
* Class Name : MemberService.java
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
package com.sist.feb.member.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.follow.domain.FollowBasicVo;
import com.sist.feb.follow.domain.FollowVO;
import com.sist.feb.member.cmn.DTO;
import com.sist.feb.member.dao.MemberDao;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.profile.image.domain.ProfileImageVO;
import com.sist.feb.storage.domain.MemberPostVO;
import com.sist.feb.storage.domain.StoreTwoVO;

/**
 * @author 123wo
 *
 */
public class MemberService {
	
	@SuppressWarnings("unused")
	private final Logger LOG = Logger.getLogger(MemberService.class);
	private MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}
	
	public MemberVO doSelectOne(DTO param) {
		
		LOG.debug("=========================================");
		LOG.debug("=MemberService-doSelectOne");
		LOG.debug("=========================================");
		MemberVO outDATA = dao.doSelectOne(param);
		
		return outDATA;
		
	}
	
	public int doUpdate(DTO param) {
		return dao.doUpdate(param);
	}
	
	public int doCountMyFollowing(DTO param) {
		return dao.doCountMyFollowing(param);
	}
	
	public int doCountMyFollowed(DTO param) {
		return dao.doCountMyFollowed(param);
	}
	
	public List<FollowVO> doInquireFollowing(DTO param){
		return dao.doInquireFollowing(param);
	}
	
	public List<FollowVO> doInquireFollowed(DTO param){
		return dao.doInquireFollowed(param);
	}
	
	public List<MemberPostVO> doInquirePost(DTO param){
		return dao.doInquirePost(param);
	}
	
	public List<StoreTwoVO> doInquireStorageSave(DTO param){
		return dao.doInquireStorageSave(param);
	}
	
	public List<StoreTwoVO> doInquireStorageLike(DTO param){
		return dao.doInquireStorageLike(param);
	}
	
	public int doUnfollow(DTO param) {
		return dao.doUnfollow(param);
	}
	
	public int doFollow(DTO param1, DTO param2) {
		return dao.doFollow(param1, param2);
	}
	
	public int doCheckFollowing(DTO param1, DTO param2) {
		return dao.doCheckFollowing(param1, param2);
	}
	
	public int doDeleteProfileImage(DTO param) {
		return dao.doDeleteProfileImage(param);
	}
	
	public int doRegisterProfileImage(DTO param) {
		return dao.doRegisterProfileImage(param);
	}

	public int doCheckProfileImage(DTO param) {
		return dao.doCheckProfileImage(param);
	}
	
	public ProfileImageVO doInquireProfileImage(DTO param) {
		return dao.doInquireProfileImage(param);
	}
	
	public FollowBasicVo doReturnFollowDetail(DTO param1, DTO param2) {
		return dao.doReturnFollowDetail(param1, param2);
	}
	
	public int doSaveOrLikePost(DTO param) {
		return dao.doSaveOrLikePost(param);
	}
	
	public int doCanselSaveOrLikePost(DTO param) {
		return dao.doCanselSaveOrLikePost(param);
	}
	
	public int doCheckSave(PostPicVO param1, MemberVO param2) {
		return dao.doCheckSave(param1, param2);
	}
	
	public int doCheckLike(PostPicVO param1, MemberVO param2) {
		return dao.doCheckLike(param1, param2);
	}
	
	
}
