/**
* <pre>
* com.sist.feb.post.cmn
* Class Name : DTO.java
* Description:
* Author: 김혜영
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
package com.sist.feb.post.cmn;

/**
 * @author khy81
 *
 */
public class PostDTO {
	
	//메시지 전달 객체
	private int msgFlag; 
	private String msg;

	public PostDTO() {}

	public int getMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(int msgFlag) {
		this.msgFlag = msgFlag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "PostDTO [msgFlag=" + msgFlag + ", msg=" + msg + "]";
	}

	
	
	
}
