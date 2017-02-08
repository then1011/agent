package com.agent.base.service;

import javax.servlet.http.HttpServletRequest;

public interface IBaseService {

	public Object invoke();

	public void init(String methodName, HttpServletRequest request);

	public void destory();

}
