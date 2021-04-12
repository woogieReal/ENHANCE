/**
* <pre>
* com.sist.feb.storage.domain
* Class Name : StorageVO.java
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
package com.sist.feb.storage.domain;

import com.sist.feb.member.cmn.DTO;

public class StorageVO extends DTO {
	
	private int storage_no;  
	private int store_type;  
	private String reg_dt;  
	private int post_no;  
	private int member_no;  
	private int pic_group;
	
	public StorageVO() {
		
	}

	public StorageVO(int storage_no, int store_type, String reg_dt, int post_no, int member_no, int pic_group) {
		super();
		this.storage_no = storage_no;
		this.store_type = store_type;
		this.reg_dt = reg_dt;
		this.post_no = post_no;
		this.member_no = member_no;
		this.pic_group = pic_group;
	}

	public int getStorage_no() {
		return storage_no;
	}

	public void setStorage_no(int storage_no) {
		this.storage_no = storage_no;
	}

	public int getStore_type() {
		return store_type;
	}

	public void setStore_type(int store_type) {
		this.store_type = store_type;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public int getPic_group() {
		return pic_group;
	}

	public void setPic_group(int pic_group) {
		this.pic_group = pic_group;
	}

	@Override
	public String toString() {
		return "StorageVO [storage_no=" + storage_no + ", store_type=" + store_type + ", reg_dt=" + reg_dt
				+ ", post_no=" + post_no + ", member_no=" + member_no + ", pic_group=" + pic_group + ", toString()="
				+ super.toString() + "]";
	}

	
}
