package dev.sharma.akash.tokenutility.libs.util.token;

import java.util.Map;

public interface TokenUtility {
	String generateToken(String subject, Map<String, Object> claims);

	String getSubject(String token);

	boolean isValidToken(String token);
}
