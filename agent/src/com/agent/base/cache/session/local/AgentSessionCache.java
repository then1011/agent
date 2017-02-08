package com.agent.base.cache.session.local;

import java.io.Serializable;
import java.util.TimerTask;

import com.agent.base.vo.AgentSession;

public class AgentSessionCache implements Serializable{
	private static final long serialVersionUID = -9009592745130097857L;

	private AgentSession session;
	private TimerTask thisThread;

	protected AgentSession getSession() {
		return session;
	}

	protected void setSession(AgentSession session) {
		this.session = session;
	}

	protected TimerTask getThisThread() {
		return thisThread;
	}

	protected void setThisThread(TimerTask thisThread) {
		this.thisThread = thisThread;
	}
	
	protected void stopThread(){
		thisThread.cancel();
	}

}
