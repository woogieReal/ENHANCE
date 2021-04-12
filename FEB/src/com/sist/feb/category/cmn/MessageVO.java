/**
* <pre>
* com.sist.feb.category.cmn
* Class Name : MessageVO.java
* Description:
* Author: 임하람
* Since: 2021/03/17
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/17 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.category.cmn;

/**
 * @author 임하람
 *
 */
public class MessageVO extends DTO {
	
	private String msgId;//1,0
	private String msgContents;//등록성공,등록실패
	private String msgDetail;//메세지 상세
	
	public MessageVO() {
			
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
		return "MessageVO [msgId=" + msgId + ", msgContents=" + msgContents + ", msgDetail=" + msgDetail
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}//--class
