package dev.sharma.akash.tokenutility.libs.util.token;

import java.util.Map;

/**
 * Utility interface for managing Tokens.
 */
public interface TokenUtility {
	/**
	 * Generates a Token with the specified subject and claims.
	 *
	 * @param subject The subject for which the token is being generated, typically
	 *                representing a user or entity.
	 * @param claims  A map of additional claims to be included in the token
	 *                payload.
	 * @return A signed Token as a string.
	 */
	String generateToken(String subject, Map<String, Object> claims);

	/**
	 * Extracts the subject from a given Token.
	 *
	 * @param token The Token from which to extract the subject.
	 * @return The subject contained within the Token.
	 */
	String getSubject(String token);

	/**
	 * Validates a given Token.
	 *
	 * @param token The Token to validate.
	 * @return {@code true} if the token is valid, {@code false} otherwise.
	 */
	boolean isValidToken(String token);
}
