package com.agent.base.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.agent.base.manager.PropertiesManager;
import com.agent.base.proxy.adapter.IBaseAdapter;
import com.agent.base.proxy.annotation.MainService;
import com.agent.base.proxy.load.ServiceBeanMap;
import com.agent.base.service.IBaseService;
import com.agent.base.vo.AgentSession;
import com.agent.base.vo.BaseParam;
import com.agent.common.constants.ThreadLocalContants;
import com.agent.common.exception.BussinessException;
import com.agent.common.exception.SystemException;

@Service(value="baseService")
public class BaseServiceImpl implements IBaseService{

	@Override
	public Object invoke() {
		BaseParam baseParam = ThreadLocalContants.getBaseParam();
		IBaseAdapter baseAdapter = ServiceBeanMap.getServiceBean(baseParam.getMethodName());
		if(baseAdapter == null){
			throw new SystemException("系统错误，请输入正确的地址");
		}
		//校验
		validation(baseAdapter);
		
		//执行前置方法
		baseAdapter.beforeInvoke();
		
		//执行主方法
		Object obj = baseAdapter.invoke();
		
		//执行后置方法
		baseAdapter.afterInvoke();
		return obj;
	}

	@Override
	public String init(String methodName, HttpServletRequest request) {
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
		
		Long expireSecond = Long.parseLong(PropertiesManager.get("session_timeout_second"));
		AgentSession session = AgentSession.getSession(uid, expireSecond);
		ThreadLocalContants.sessionLocal.set(session);
		return session.getId();
	}

	@Override
	public void destory() {
		ThreadLocalContants.getSession().refresh();
		ThreadLocalContants.sessionLocal.remove();
		ThreadLocalContants.baseParamLocal.remove();
	}
	
	private void validation(IBaseAdapter baseAdapter){
		//判断是否需要登录
		MainService config = baseAdapter.getClass().getAnnotation(MainService.class);
		if(config.needLogin()){
			AgentSession session = ThreadLocalContants.getSession();
			if(session == null || StringUtils.isBlank(session.getLoginId())){
				throw new BussinessException("业务错误，请先登录");
			}
		}
	}
	
	
}
