package dev.sharma.akash.tokenutility.libs.service;

import java.util.Map;

/**
 * Service interface for managing access and refresh tokens, handling token
 * blacklisting, and additional token-related operations.
 */
public interface TokenService {

	/**
	 * Generates a token with the specified subject.
	 *
	 * @param subject The subject for which the token is being generated, typically
	 *                representing a user or entity.
	 * @return A signed token as a string.
	 */
	String generateToken(String subject);

	/**
	 * Generates a token with the specified subject and claims.
	 *
	 * @param subject The subject for which the token is being generated, typically
	 *                representing a user or entity.
	 * @param claims  A map of additional claims to be included in the refresh token
	 *                payload.
	 * @return A signed token as a string.
	 */
	String generateToken(String subject, Map<String, Object> claims);

	/**
	 * Validates a given token.
	 *
	 * @param token The token to validate.
	 * @return {@code true} if the refresh token is valid, {@code false} otherwise.
	 */
	boolean isValidToken(String token);

	/**
	 * Blacklists a given token with a specified expiration time. This method adds
	 * the token to the blacklist and ensures it is invalidated until the expiration
	 * time.
	 *
	 * @param token          The token to be blacklisted.
	 * @param expirationTime The expiration time in seconds since the epoch when the
	 *                       token should be removed from the blacklist.
	 */
	void blacklistToken(String token, long expirationTime);
}
