package com.agent.service.special;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;

@MainService(id = "FamilySpecialInService", needLogin=true)
public class FamilySpecialInService extends BaseAdapter{

	@Override
	public Object invoke() {
		return "3";
	}

}
