/**
* <pre>
* com.sist.feb.storage.domain
* Class Name : MemberPostVO.java
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
package com.sist.feb.storage.domain;

import com.sist.feb.member.cmn.DTO;

public class MemberPostVO extends DTO{
	
	private int postNo; 
	private int picNo; 
	private int memberNo;
	private String path;
	
	public MemberPostVO() {
		
	}

	public MemberPostVO(int postNo, int picNo, int memberNo, String path) {
		super();
		this.postNo = postNo;
		this.picNo = picNo;
		this.memberNo = memberNo;
		this.path = path;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getPicNo() {
		return picNo;
	}

	public void setPicNo(int picNo) {
		this.picNo = picNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "MemberPostVO [postNo=" + postNo + ", picNo=" + picNo + ", memberNo=" + memberNo + ", path=" + path
				+ ", toString()=" + super.toString() + "]";
	}

	
	
	
	
}
