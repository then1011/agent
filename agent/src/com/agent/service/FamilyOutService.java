package com.agent.service;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;

@MainService(id = "FamilyOutService")
public class FamilyOutService extends BaseAdapter{

	@Override
	public Object invoke() {
		return "1";
	}

}
