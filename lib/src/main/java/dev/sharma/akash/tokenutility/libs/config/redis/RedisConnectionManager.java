package dev.sharma.akash.tokenutility.libs.config.redis;

import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPooled;

public class RedisConnectionManager {

	private static JedisPooled jedisPooled;

	private static final int DEFAULT_MAX_ACTIVE_CONNECTION = 20;
	private static final int DEFAULT_MAX_IDLE_CONNECTION = 10;

	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 6379;

	public static synchronized JedisPooled getInstance() {
		if (jedisPooled == null) {
			initialiseJedisPooled();
		}
		return jedisPooled;
	}

	private static void initialiseJedisPooled() {
		ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
		poolConfig.setMaxIdle(DEFAULT_MAX_IDLE_CONNECTION);
		poolConfig.setMaxTotal(DEFAULT_MAX_ACTIVE_CONNECTION);

		jedisPooled = new JedisPooled(poolConfig, DEFAULT_HOST, DEFAULT_PORT);
	}
}
