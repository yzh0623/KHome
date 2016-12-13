package com.kida.home.bean.vo;

public class MessageVO {

	private String msgCode;// 消息代码
	private String msgInfo;// 消息主题

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		/*
		 * try { this.msgInfo = new String(msgInfo.getBytes("UTF-8"), "UTF-8");
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		this.msgInfo = msgInfo;
	}

}
