package com.agent.test;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;

@MainService(id="xiaobai")
public class TestService extends BaseAdapter{

	@Override
	public Object invoke() {
		return null;
	}

}
