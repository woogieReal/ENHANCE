/**
* <pre>
* com.sist.feb.post.domain
* Class Name : PostVO.java
* Description:
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
package com.sist.feb.post.domain;

import com.sist.feb.post.cmn.PostDTO;

/**
 * @author khy81
 *
 */
public class PostVO extends PostDTO {
	private int 	postNo      ;  //게시물번호
	private int 	memberNo    ;  //멤버번호
	private String 	title        ;  //게시물제목
	private String 	contents     ;  //게시물내용
	private int 	readCnt     ;  //조회수
	private String 	regDt       ;  //게시일
	private String 	modDt       ;  //수정일
	private int 	picNo       ;  //이미지번호picNo
	private int 	picGroup    ;  //이미지그룹
	private int 	categorySeq ;  //분야번호
	private int 	tagGroup    ;  //태그그룹
	
	public PostVO() {
		
	}

	public PostVO(int postNo, int memberNo, String title, String contents, int readCnt, String regDt, String modDt,
			int picNo, int picGroup, int categorySeq, int tagGroup) {
		super();
		this.postNo = postNo;
		this.memberNo = memberNo;
		this.title = title;
		this.contents = contents;
		this.readCnt = readCnt;
		this.regDt = regDt;
		this.modDt = modDt;
		this.picNo = picNo;
		this.picGroup = picGroup;
		this.categorySeq = categorySeq;
		this.tagGroup = tagGroup;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public int getPicNo() {
		return picNo;
	}

	public void setPicNo(int picNo) {
		this.picNo = picNo;
	}

	public int getPicGroup() {
		return picGroup;
	}

	public void setPicGroup(int picGroup) {
		this.picGroup = picGroup;
	}

	public int getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}

	public int getTagGroup() {
		return tagGroup;
	}

	public void setTagGroup(int tagGroup) {
		this.tagGroup = tagGroup;
	}

	@Override
	public String toString() {
		return "PostVO [postNo=" + postNo + ", memberNo=" + memberNo + ", title=" + title + ", contents=" + contents
				+ ", readCnt=" + readCnt + ", regDt=" + regDt + ", modDt=" + modDt + ", picNo=" + picNo + ", picGroup="
				+ picGroup + ", categorySeq=" + categorySeq + ", tagGroup=" + tagGroup + ", toString()="
				+ super.toString() + "]";
	}

	
	

}
