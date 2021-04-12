/**
* <pre>
* com.sist.feb.reply.domain
* Class Name : ReplyVO.java
* Description:
* Author: hansol
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
package com.sist.feb.reply.domain;

import com.sist.feb.reply.cmn.DTO;

/**
 * @author hansol
 *
 */
public class ReplyVO extends DTO{
	
	private int reply_no;		//댓글번호
	private int post_no; 		//게시물번호
	private String contents;	//댓글내용
	private String reg_dt 	;	//등록일
	private String mod_dt 	;	//수정일
	private int member_no;	//멤버번호
	
	public ReplyVO() {
		
	}

	public ReplyVO(int reply_no, int post_no, String contents, String reg_dt, String mod_dt, int member_no) {
		super();
		this.reply_no = reply_no;
		this.post_no = post_no;
		this.contents = contents;
		this.reg_dt = reg_dt;
		this.mod_dt = mod_dt;
		this.member_no = member_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	@Override
	public String toString() {
		return "ReplyVO [reply_no=" + reply_no + ", post_no=" + post_no + ", contents=" + contents + ", reg_dt="
				+ reg_dt + ", mod_dt=" + mod_dt + ", member_no=" + member_no + ", toString()=" + super.toString() + "]";
	}


	
}
