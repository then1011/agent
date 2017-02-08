package com.agent.base.proxy.load;

import java.util.Map;

import com.agent.base.proxy.adapter.IBaseAdapter;
import com.agent.common.exception.SystemException;

public class ServiceBeanMap {

	private static Map<String, IBaseAdapter> serviceBeanMap;
	
	private ServiceBeanMap(){};
	
	public static IBaseAdapter getServiceBean(String serviceId){
		if(serviceBeanMap == null || serviceBeanMap.isEmpty()){
			throw new SystemException("系统错误，初始化服务失败，请重新初始化");
		}
		return serviceBeanMap.get(serviceId);
	}
	
	public static void init(Map<String, IBaseAdapter> serviceBeanMapTemp){
		serviceBeanMap = serviceBeanMapTemp;
	}
}
