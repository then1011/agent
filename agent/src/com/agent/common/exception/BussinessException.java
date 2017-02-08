package com.agent.common.exception;

public class BussinessException extends RuntimeException{

	private static final long serialVersionUID = -6515943773393179293L;
	public BussinessException() {
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
