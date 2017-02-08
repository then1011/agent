package com.agent.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agent.base.service.IBaseService;
import com.agent.base.vo.Result;
import com.agent.common.exception.BussinessException;
import com.agent.common.exception.SystemException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class MainController {
	
	// �����쳣��־��¼����
	protected transient final Logger log = Logger.getLogger(MainController.class);
	
	@Autowired
	private IBaseService baseService;
	
	@RequestMapping("/MainMethod/{methodName}")
	@ResponseBody
	public Object mainMethod(@PathVariable String methodName, HttpServletRequest request){
		Result result = new Result();
		try {
			log.info("���÷�����" +methodName);
			
			baseService.init(methodName, request);
			Object obj = baseService.invoke();
			baseService.destory();
			
			log.info("���ؽ����" + JSON.toJSONString(obj));
			result.setMsg("Success");
			result.setContent(obj);
		} catch(SystemException e) {
			log.error(e);
			result.setCode(-1);
			result.setMsg(e.getMessage());
		} catch (BussinessException e){
			log.error(e);
			result.setCode(-2);
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			log.error(e);
			result.setCode(-1);
			result.setMsg("ϵͳ����");
		}
		return JSONObject.toJSONString(result);
	}

}