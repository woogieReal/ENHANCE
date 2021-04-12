/**
* <pre>
* com.sist.feb.picture.domain
* Class Name : PictureVO.java
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
package com.sist.feb.picture.domain;

import com.sist.feb.post.cmn.PostDTO;

/**
 * @author khy81
 *
 */
public class PictureVO extends PostDTO {
	
	private int    picNo    ; //이미지번호(IMAGE_SEQ)
	private int    picGroup ; //이미지그룹(IMG_GROUP_SEQ)
	private String path     ; //이미지경로
	private String picNm    ; //원본파일명
	private String saveNm   ; //저장파일명
	
	public PictureVO() {
		
	}

	public PictureVO(int picNo, int picGroup, String path, String picNm, String saveNm) {
		super();
		this.picNo = picNo;
		this.picGroup = picGroup;
		this.path = path;
		this.picNm = picNm;
		this.saveNm = saveNm;
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

	public String getPicNm() {
		return picNm;
	}

	public void setPicNm(String picNm) {
		this.picNm = picNm;
	}

	public String getSaveNm() {
		return saveNm;
	}

	public void setSaveNm(String saveNm) {
		this.saveNm = saveNm;
	}

	@Override
	public String toString() {
		return "PictureVO [picNo=" + picNo + ", picGroup=" + picGroup + ", path=" + path + ", picNm=" + picNm
				+ ", saveNm=" + saveNm + ", toString()=" + super.toString() + "]";
	}
	
	

}
