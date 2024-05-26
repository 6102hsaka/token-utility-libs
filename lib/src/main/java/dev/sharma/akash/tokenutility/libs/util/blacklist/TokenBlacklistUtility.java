package dev.sharma.akash.tokenutility.libs.util.blacklist;

public interface TokenBlacklistUtility {
	void blacklistToken(String token, long expirationTime);

	boolean isTokenBlacklisted(String token);
}
