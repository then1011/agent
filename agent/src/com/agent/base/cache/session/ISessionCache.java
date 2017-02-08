package com.agent.base.cache.session;

import com.agent.base.vo.AgentSession;

public interface ISessionCache {
	AgentSession getSessionCache(String uid);
	void setSessionCache(final AgentSession session);
}
