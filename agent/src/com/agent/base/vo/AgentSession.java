package com.agent.base.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.agent.base.manager.SessionManager;

public class AgentSession implements Serializable{
	private static final long serialVersionUID = 7599665512053346649L;
	
	private String id;
	private Long expireSecond;
	private String secretKey;
	private String loginId;
	
	private Map<String, Object> paramMap = new HashMap<String, Object>();
	
	private AgentSession(String uid, Long expireSecond){
		if(StringUtils.isBlank(uid)){
			id = UUID.randomUUID().toString();
		}else{
			id = uid;
		}
		this.expireSecond = expireSecond;
	}
	
	public static AgentSession getSession(String uid, Long expireSecond){
		AgentSession session = SessionManager.getSession(uid);
		if(session == null || StringUtils.isBlank(session.getId())){
			session = new AgentSession(uid, expireSecond);
		}
		SessionManager.refreshSession(session);
		return session;
	}
	
	public void refresh(){
        SessionManager.refreshSession(this);
	}

	public String getId() {
		return id;
	}


	public Long getExpireSecond() {
		return expireSecond;
	}


	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public void setAttr(String key, Object value){
		paramMap.put(key, value);
	}
	
	public Object getAttr(String key){
		return paramMap.get(key);
	}
	
	
	
}
