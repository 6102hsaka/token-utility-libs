package dev.sharma.akash.tokenutility.libs.util.blacklist;

import dev.sharma.akash.tokenutility.libs.common.Constant;
import redis.clients.jedis.JedisPooled;

public class RedisTokenBlacklistUtility implements TokenBlacklistUtility {

	private final JedisPooled jedisPooled;

	public RedisTokenBlacklistUtility(JedisPooled jedisPooled) {
		this.jedisPooled = jedisPooled;
	}

	@Override
	public void blacklistToken(String token, long expirationTime) {
		jedisPooled.setex(token, expirationTime, Constant.BLACKLISTED);
	}

	@Override
	public boolean isTokenBlacklisted(String token) {
		return jedisPooled.exists(token) && Constant.BLACKLISTED.equals(jedisPooled.get(token));
	}

}
