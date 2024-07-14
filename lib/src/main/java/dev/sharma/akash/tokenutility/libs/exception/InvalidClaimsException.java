package dev.sharma.akash.tokenutility.libs.exception;

/**
 * Exception to be thrown when the JWT claims is invalid.
 * 
 */
public class InvalidClaimsException extends RuntimeException {

	private static final long serialVersionUID = -428953401191919763L;

	public InvalidClaimsException(String message) {
		super(message);
	}

}
