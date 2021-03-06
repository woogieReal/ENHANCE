/**
* <pre>
* com.sist.feb.follow.domain
* Class Name : FollowVO.java
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
package com.sist.feb.follow.domain;

import com.sist.feb.member.cmn.DTO;

public class FollowVO extends DTO {
	
	private int followNo;
	private int followingNo;
	private String name;
	private String location;
	private String followDt;
	private int theirFollowedCount;
	
	public FollowVO() {
		
	}

	public FollowVO(int followNo, int followingNo, String name, String location, String followDt,
			int theirFollowedCount) {
		super();
		this.followNo = followNo;
		this.followingNo = followingNo;
		this.name = name;
		this.location = location;
		this.followDt = followDt;
		this.theirFollowedCount = theirFollowedCount;
	}

	public int getFollowNo() {
		return followNo;
	}

	public void setFollowNo(int followNo) {
		this.followNo = followNo;
	}

	public int getFollowingNo() {
		return followingNo;
	}

	public void setFollowingNo(int followingNo) {
		this.followingNo = followingNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFollowDt() {
		return followDt;
	}

	public void setFollowDt(String followDt) {
		this.followDt = followDt;
	}

	public int getTheirFollowedCount() {
		return theirFollowedCount;
	}

	public void setTheirFollowedCount(int theirFollowedCount) {
		this.theirFollowedCount = theirFollowedCount;
	}

	@Override
	public String toString() {
		return "FollowVO [followNo=" + followNo + ", followingNo=" + followingNo + ", name=" + name + ", location="
				+ location + ", followDt=" + followDt + ", theirFollowedCount=" + theirFollowedCount + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
