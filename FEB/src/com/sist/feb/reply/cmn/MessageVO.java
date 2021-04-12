package com.sist.feb.reply.cmn;

public class MessageVO extends DTO {

	private String msgId;		//1,0
	private String msgContents; //등록, 성공 실패
	private String msgDetail;	//메시지 상태
	
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
	
	
}
