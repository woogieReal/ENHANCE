/**
* <pre>
* com.sist.feb.profile.image.domain
* Class Name : ProfileImage.java
* Description:
* Author: 임하람
* Since: 2021/03/15
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/15 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.profile.image.domain;

import com.sist.feb.member.cmn.DTO;

public class ProfileImageVO extends DTO{
	
	private String fileId; //파일ID
	private String orgFileNm; //원본파일명
	private String saveFileNm; //저장파일명
	private String path; //저장경로
	private long size; //파일사이즈
	private String ext; //확장자
	private String regDt;//등록일
	private int memberNo; //회원ID
	
	public ProfileImageVO() {
		
	}

	public ProfileImageVO(String fileId, String orgFileNm, String saveFileNm, String path, long size, String ext,
			String regDt, int memberNo) {
		super();
		this.fileId = fileId;
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.path = path;
		this.size = size;
		this.ext = ext;
		this.regDt = regDt;
		this.memberNo = memberNo;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getOrgFileNm() {
		return orgFileNm;
	}

	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}

	public String getSaveFileNm() {
		return saveFileNm;
	}

	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "ProfileImageVO [fileId=" + fileId + ", orgFileNm=" + orgFileNm + ", saveFileNm=" + saveFileNm
				+ ", path=" + path + ", size=" + size + ", ext=" + ext + ", regDt=" + regDt + ", memberNo=" + memberNo
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
