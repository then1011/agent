package com.agent.base.cache.session.local;

import com.agent.base.cache.session.ISessionCache;
import com.agent.base.vo.AgentSession;

public class SessionLocalCacheImpl implements ISessionCache{

	@Override
	public AgentSession getSessionCache(String uid) {
		return SessionCacheMap.getSessionCache(uid);
	}

	@Override
	public void setSessionCache(AgentSession session) {
		SessionCacheMap.setSessionCache(session);
	}

}
