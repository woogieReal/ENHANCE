/**
* <pre>
* com.sist.feb.post.cmn
* Class Name : MessageVO.java
* Description:
* Author: 
* Since: 2021/03/12
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/12 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.post.cmn;

/**
 * @author khy81
 *
 */
public class PostMsgVO extends PostDTO {
	
	private String msgId; //1성공, 0실패
	private String msgContents;//1등록성공,0실패
	private String msgDetail;//메시지 상세
	
	public PostMsgVO() {
		
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgContents() {
		return msgContents;
	}

	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	public String getMsgDetail() {
		return msgDetail;
	}

	public void setMsgDetail(String msgDetail) {
		this.msgDetail = msgDetail;
	}

	@Override
	public String toString() {
		return "PostMsgVO [msgId=" + msgId + ", msgContents=" + msgContents + ", msgDetail=" + msgDetail
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
