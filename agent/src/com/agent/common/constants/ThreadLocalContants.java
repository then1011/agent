package com.agent.common.constants;

import com.agent.base.vo.AgentSession;
import com.agent.base.vo.BaseParam;

public class ThreadLocalContants {
	
	public static ThreadLocal<AgentSession> sessionLocal = new ThreadLocal<AgentSession>();
	public static ThreadLocal<BaseParam> baseParamLocal = new ThreadLocal<BaseParam>();
	
	public static AgentSession getSession(){
		return sessionLocal.get();
	}
	
	
	public static BaseParam getBaseParam(){
		return baseParamLocal.get();
	}
	
	public static Object getParam(){
		return getBaseParam().getParam();
	}
	
	public static String getUid(){
		return getBaseParam().getUid();
	}

}
