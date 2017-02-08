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
			throw new SystemException("ϵͳ������������ȷ�ĵ�ַ");
		}
		//У��
		validation(baseAdapter);
		
		//ִ��ǰ�÷���
		baseAdapter.beforeInvoke();
		
		//ִ��������
		Object obj = baseAdapter.invoke();
		
		//ִ�к��÷���
		baseAdapter.afterInvoke();
		return obj;
	}

	@Override
	public String init(String methodName, HttpServletRequest request) {
		if(StringUtils.isBlank(methodName)){
			throw new SystemException("ϵͳ����δ֪���÷���");
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
		//�ж��Ƿ���Ҫ��¼
		MainService config = baseAdapter.getClass().getAnnotation(MainService.class);
		if(config.needLogin()){
			AgentSession session = ThreadLocalContants.getSession();
			if(session == null || StringUtils.isBlank(session.getLoginId())){
				throw new BussinessException("ҵ��������ȵ�¼");
			}
		}
	}
	
	
}
