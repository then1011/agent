package com.agent.service.special;

import com.agent.base.proxy.adapter.impl.BaseAdapter;
import com.agent.base.proxy.annotation.MainService;
import com.agent.base.vo.AgentSession;
import com.agent.common.constants.ThreadLocalContants;

@MainService(id = "FamilySpecialInService", needLogin=true)
public class FamilySpecialInService extends BaseAdapter{

	@Override
	public Object invoke() {
		AgentSession session = ThreadLocalContants.getSession();
		return "session:" + session.getLoginId();
	}

}
