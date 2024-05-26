package token.utility.libs.service.blacklist;

import redis.clients.jedis.JedisPooled;
import token.utility.libs.common.Constant;

public class RedisTokenBlacklistService implements TokenBlacklistService {

	private final JedisPooled jedisPooled;

	public RedisTokenBlacklistService(JedisPooled jedisPooled) {
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
