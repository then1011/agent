package com.agent.base.cache.session;

import com.agent.base.cache.session.local.SessionLocalCacheImpl;
import com.agent.base.cache.session.redis.SessionRedisCacheImpl;
import com.agent.base.manager.PropertiesManager;
import com.agent.common.exception.SystemException;

public class SessionCacheFactory {
	
	public static ISessionCache sessionCache;

	private SessionCacheFactory(){}
	
	public static ISessionCache getSessionCache(){
		if(sessionCache == null){
			init();
		}
		return sessionCache;
	}
	
	private static void init(){
		String sessionCacheType = PropertiesManager.get("session_cache_type");
		if("local".equals(sessionCacheType)){
			sessionCache = new SessionLocalCacheImpl();
		}else if("redis".equals(sessionCacheType)){
			sessionCache = new SessionRedisCacheImpl();
		}else{
			throw new SystemException("系统错误，获取session缓存方式失败");
		}
	}
}
