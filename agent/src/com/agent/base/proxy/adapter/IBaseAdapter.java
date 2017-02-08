package com.agent.base.proxy.adapter;

public interface IBaseAdapter {
	void beforeInvoke();
	Object invoke();
	void afterInvoke();
}
