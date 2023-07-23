package com.seungwon.blog.application.exception;

public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException() {
	}

	public InvalidRequestException(String message) {
		super(message);
	}
}
