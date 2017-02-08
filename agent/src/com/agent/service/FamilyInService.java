package com.agent.service;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;

@MainService(id = "FamilyInService")
public class FamilyInService extends BaseAdapter{

	@Override
	public Object invoke() {
		return "2";
	}

}
