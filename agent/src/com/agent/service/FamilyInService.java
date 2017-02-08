package com.agent.service;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;
import com.agent.base.vo.AgentSession;
import com.agent.common.constants.ThreadLocalContants;

@MainService(id = "FamilyInService")
public class FamilyInService extends BaseAdapter{

	@Override
	public Object invoke() {
		AgentSession session = ThreadLocalContants.getSession();
		session.setLoginId("123");
		
		return "session:" + session.getLoginId();
	}

}
