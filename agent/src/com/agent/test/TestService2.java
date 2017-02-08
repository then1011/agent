package com.agent.test;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;

@MainService(id="xiaohei")
public class TestService2 extends BaseAdapter{

	@Override
	public Object invoke() {
		return null;
	}

}
