package com.agent.base.vo;

import java.io.Serializable;

public class BaseParam implements Serializable {

	private static final long serialVersionUID = 7596686234643092533L;

	private String methodName;
	private String uid;
	private Object param;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

}
