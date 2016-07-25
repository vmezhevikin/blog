package com.vmezhevikin.blog.exception;

public class CantCompleteClientRequestException extends RuntimeException {

	private static final long serialVersionUID = 4145836803427181631L;

	public CantCompleteClientRequestException(String message) {
		super(message);
	}

	public CantCompleteClientRequestException(Throwable cause) {
		super(cause);
	}

	public CantCompleteClientRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}