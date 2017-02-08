package com.agent.base.cache.session.local;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.agent.base.vo.AgentSession;

public class SessionCacheMap {
	private static Map<String, AgentSessionCache> sessionMap;

	static {
		if (sessionMap == null) {
			sessionMap = new HashMap<String, AgentSessionCache>();
		}
	}

	protected static AgentSession getSessionCache(String uid) {
		AgentSessionCache sessionCache = sessionMap.get(uid);
		if (sessionCache != null) {
			return sessionCache.getSession();
		}
		return null;
	}

	protected static void setSessionCache(final AgentSession session) {
		if (session != null && !StringUtils.isBlank(session.getId())) {
			//ɾ���ɵ�Session�Ķ�ʱ��
			AgentSessionCache oldSession = sessionMap.get(session.getId());
			if (oldSession != null) {
				oldSession.stopThread();
			}

			//�����µ�session
			AgentSessionCache sessionCache = new AgentSessionCache();
			sessionCache.setSession(session);
			
			//�趨session��ʱ��
			sessionCache.setThisThread(new TimerTask() {
				@Override
				public void run() {
					sessionMap.remove(session.getId());
				}
			});

			sessionMap.put(session.getId(), sessionCache);
			
			//����session��ʱ��
			Timer timer = new Timer();
			timer.schedule(sessionCache.getThisThread(), session.getExpireSecond() * 1000);
		}
	}

}
