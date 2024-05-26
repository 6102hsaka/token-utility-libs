package token.utility.libs.service.blacklist;

public interface TokenBlacklistService {
	void blacklistToken(String token, long expirationTime);

	boolean isTokenBlacklisted(String token);
}
