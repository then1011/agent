package com.agent.base.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.agent.base.manager.PropertiesManager;
import com.agent.base.service.IBaseService;
import com.agent.base.vo.AgentSession;
import com.agent.base.vo.BaseParam;
import com.agent.common.constants.ThreadLocalContants;
import com.agent.common.exception.SystemException;

@Service(value="baseService")
public class BaseServiceImpl implements IBaseService{

	@Override
	public Object invoke() {
		System.out.println(PropertiesManager.get("cache_type"));
		
		return null;
	}

	@Override
	public void init(String methodName, HttpServletRequest request) {
		if(StringUtils.isBlank(methodName)){
			throw new SystemException("系统错误，未知调用方法");
		}
		String uid = request.getParameter("uid");
		String q = request.getParameter("q");
		
		BaseParam baseParam = new BaseParam();
		baseParam.setMethodName(methodName);
		baseParam.setUid(uid);
		baseParam.setParam(q);
		ThreadLocalContants.baseParamLocal.set(baseParam);
		
		AgentSession session = AgentSession.getSession(uid, 1800l);
		ThreadLocalContants.sessionLocal.set(session);
	}

	@Override
	public void destory() {
		ThreadLocalContants.getSession().refresh();
		ThreadLocalContants.sessionLocal.remove();
		ThreadLocalContants.baseParamLocal.remove();
	}
	
	
}
