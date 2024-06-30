package dev.sharma.akash.tokenutility.libs.exception;

/**
 * Exception to be thrown when the JWT subject is invalid.
 * 
 */
public class InvalidSubjectException extends RuntimeException {

	private static final long serialVersionUID = 4224982333869894181L;

	public InvalidSubjectException(String message) {
		super(message);
	}

}
