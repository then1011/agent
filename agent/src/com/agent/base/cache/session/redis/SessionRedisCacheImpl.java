package com.agent.base.cache.session.redis;

import com.agent.base.cache.session.ISessionCache;
import com.agent.base.vo.AgentSession;

public class SessionRedisCacheImpl implements ISessionCache{

	@Override
	public AgentSession getSessionCache(String uid) {
		return null;
	}

	@Override
	public void setSessionCache(AgentSession session) {
		
	}

}
