package dev.sharma.akash.tokenutility.libs.service;

import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.sharma.akash.tokenutility.libs.common.Constant;
import dev.sharma.akash.tokenutility.libs.config.redis.RedisConnectionManager;
import dev.sharma.akash.tokenutility.libs.exception.InvalidSubjectException;
import dev.sharma.akash.tokenutility.libs.exception.InvalidTokenException;
import dev.sharma.akash.tokenutility.libs.util.blacklist.RedisTokenBlacklistUtility;
import dev.sharma.akash.tokenutility.libs.util.token.JwtTokenUtility;
import io.jsonwebtoken.lang.Collections;

public class JwtTokenService implements TokenService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final RedisTokenBlacklistUtility redisTokenBlacklistUtility;
	private final JwtTokenUtility jwtTokenUtility;
	private final ResourceBundle errorMessages;

	public JwtTokenService(String secret, Long expiration) {
		redisTokenBlacklistUtility = new RedisTokenBlacklistUtility(RedisConnectionManager.getInstance());
		this.jwtTokenUtility = new JwtTokenUtility(secret, expiration);
		errorMessages = ResourceBundle.getBundle(Constant.ERROR_MESSAGES_FILE_NAME);
	}

	@Override
	public String generateToken(String subject) {
		return generateToken(subject, Collections.emptyMap());
	}

	@Override
	public String generateToken(String subject, Map<String, Object> claims) {
		if (subject == null || subject.isBlank()) {
			String message = errorMessages.getString(Constant.EMPTY_SUBJECT_MESSAGE);
			logger.error(message);
			throw new InvalidSubjectException(message);
		}
		if (claims == null) {
			String message = errorMessages.getString(Constant.EMPTY_CLAIMS_MESSAGE);
			logger.error(message);
			throw new InvalidSubjectException(message);
		}
		return jwtTokenUtility.generateToken(subject, claims);
	}

	@Override
	public boolean isValidToken(String token) {
		if (token == null || token.isBlank()) {
			String message = errorMessages.getString(Constant.EMPTY_TOKEN_MESSAGE);
			logger.error(message);
			throw new InvalidTokenException(message);
		}
		if (redisTokenBlacklistUtility.isTokenBlacklisted(token)) {
			logger.info("token is blacklisted");
			return false;
		}
		return jwtTokenUtility.isValidToken(token);
	}

	@Override
	public void blacklistToken(String token, long expirationTime) {
		if (token == null || token.isBlank()) {
			String message = errorMessages.getString(Constant.EMPTY_TOKEN_MESSAGE);
			logger.error(message);
			throw new InvalidTokenException(message);
		}
		redisTokenBlacklistUtility.blacklistToken(token, expirationTime);
	}
}
