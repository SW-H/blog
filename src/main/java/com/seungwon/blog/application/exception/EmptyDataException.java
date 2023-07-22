package com.seungwon.blog.application.exception;

public class EmptyDataException extends RuntimeException {
	public EmptyDataException() {
	}

	public EmptyDataException(String message) {
		super(message);
	}
}
