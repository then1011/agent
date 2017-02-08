package com.agent.common.exception;

public class SystemException extends RuntimeException{

	private static final long serialVersionUID = -8768879957477361431L;
	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
