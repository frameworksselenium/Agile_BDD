package com.open.qa.core.exception;

public class Exceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public Exceptions(String message) {
		super(message);
		log(message);
	}

	public Exceptions(Throwable cause) {
		super(cause);
		log(cause.getMessage());
	}

	public Exceptions(String message, Throwable cause) {
		super(message, cause);
	}

	private void log(String message){
		//Logger.log("FAIL", message);
	}
}
