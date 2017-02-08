package com.agent.base.vo;

import java.io.Serializable;

public class Result implements Serializable{

	private static final long serialVersionUID = 7531251023504736993L;
	
	//0:����    -1��ϵͳ����   -2ҵ�����
	private int code = 0;
	private String msg;
	private Object content;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}

}
