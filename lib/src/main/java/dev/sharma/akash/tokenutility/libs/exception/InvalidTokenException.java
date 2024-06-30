package dev.sharma.akash.tokenutility.libs.exception;

/**
 * Exception to be thrown when the JWT token is invalid.
 * 
 */
public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = -3319631033525691966L;

	public InvalidTokenException(String message) {
		super(message);
	}

}
