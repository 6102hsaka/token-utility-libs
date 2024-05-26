package dev.sharma.akash.tokenutility.libs.util.blacklist;

/**
 * Utility interface for managing blacklisted tokens.
 */
public interface TokenBlacklistUtility {
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

	/**
	 * Checks if a given token is blacklisted. This method verifies whether the
	 * token is currently in the blacklist.
	 *
	 * @param token The token to check.
	 * @return {@code true} if the token is blacklisted, {@code false} otherwise.
	 */
	boolean isTokenBlacklisted(String token);
}
