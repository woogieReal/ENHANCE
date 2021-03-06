/**
* <pre>
* com.sist.feb.category.domain
* Class Name : CatePostVO.java
* Description:
* Author: 임하람
* Since: 2021/03/11
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/11 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.category.domain;

import com.sist.feb.category.cmn.DTO;

/**
 * @author 임하람
 *
 */
public class CatePostVO extends DTO{
	
	private int categorySeq;   //분야번호 
	private String cateName;   //분야이름
	private int postNo;        //게시물번호
	private int memberNo;      //멤버번호
	private String memberName; //멤버이름
	private String title;      //게시물제목
	private int readCnt;       //조회수
	private int picNo;         //이미지번호
	private int picGroup;      //이미지 그룹
	private String path;       //이미지 경로
	
	
	
	public CatePostVO() { }//default 생성자



	/**
	 * @param categorySeq
	 * @param cateName
	 * @param postNo
	 * @param memberNo
	 * @param memberName
	 * @param title
	 * @param readCnt
	 * @param picNo
	 * @param picGroup
	 * @param path
	 */
	public CatePostVO(int categorySeq, String cateName, int postNo, int memberNo, String memberName, String title,
			int readCnt, int picNo, int picGroup, String path) {
		super();
		this.categorySeq = categorySeq;
		this.cateName = cateName;
		this.postNo = postNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.title = title;
		this.readCnt = readCnt;
		this.picNo = picNo;
		this.picGroup = picGroup;
		this.path = path;
	}



	public int getCategorySeq() {
		return categorySeq;
	}



	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}



	public String getCateName() {
		return cateName;
	}



	public void setCateName(String cateName) {
		this.cateName = cateName;
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



	public String getMemberName() {
		return memberName;
	}



	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getReadCnt() {
		return readCnt;
	}



	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
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



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	@Override
	public String toString() {
		return "CatePostVO [categorySeq=" + categorySeq + ", cateName=" + cateName + ", postNo=" + postNo
				+ ", memberNo=" + memberNo + ", memberName=" + memberName + ", title=" + title + ", readCnt=" + readCnt
				+ ", picNo=" + picNo + ", picGroup=" + picGroup + ", path=" + path + ", toString()=" + super.toString()
				+ "]";
	}



	

}//--class
