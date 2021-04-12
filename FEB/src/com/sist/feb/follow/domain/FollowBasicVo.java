/**
* <pre>
* com.sist.feb.follow.domain
* Class Name : FollowBasicVo.java
* Description:
* Author: 임하람
* Since: 2021/03/29
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/29 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.follow.domain;

import com.sist.feb.member.cmn.DTO;

public class FollowBasicVo extends DTO {
	
	private int follow_no; 
	private int following_no; 
	private int followed_no; 
	private String follow_dt;
	
	public FollowBasicVo() {
		
	}

	public FollowBasicVo(int follow_no, int following_no, int followed_no, String follow_dt) {
		super();
		this.follow_no = follow_no;
		this.following_no = following_no;
		this.followed_no = followed_no;
		this.follow_dt = follow_dt;
	}

	public int getFollow_no() {
		return follow_no;
	}

	public void setFollow_no(int follow_no) {
		this.follow_no = follow_no;
	}

	public int getFollowing_no() {
		return following_no;
	}

	public void setFollowing_no(int following_no) {
		this.following_no = following_no;
	}

	public int getFollowed_no() {
		return followed_no;
	}

	public void setFollowed_no(int followed_no) {
		this.followed_no = followed_no;
	}

	public String getFollow_dt() {
		return follow_dt;
	}

	public void setFollow_dt(String follow_dt) {
		this.follow_dt = follow_dt;
	}

	@Override
	public String toString() {
		return "FollowBasicVo [follow_no=" + follow_no + ", following_no=" + following_no + ", followed_no="
				+ followed_no + ", follow_dt=" + follow_dt + "]";
	}
	
}
