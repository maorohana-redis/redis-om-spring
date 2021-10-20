package com.redis.spring.client;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.google.gson.GsonBuilder;
import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.redisai.RedisAI;
import com.redislabs.redisgraph.impl.api.RedisGraph;
import com.redislabs.redistimeseries.RedisTimeSeries;

import redis.clients.jedis.Jedis;

public class RedisModulesClient {

  public RedisModulesClient(JedisConnectionFactory jedisConnectionFactory) {
    this.jedisConnectionFactory = jedisConnectionFactory;
  }

  public JReJSON clientForJSON() {
    return new JReJSON(getJedis());
  };
  
  public JReJSON clientForJSON(GsonBuilder builder) {
    JReJSON client = new JReJSON(getJedis());
    client.setGsonBuilder(builder);
    return client;
  };

  public RedisGraph clientForGraph() {
    return new RedisGraph(getJedis());
  }

  public RedisTimeSeries clientForTimeSeries() {
    return new RedisTimeSeries(getJedis());
  }

  public io.redisearch.Client clientForSearch(String index) {
    return new io.redisearch.client.Client(index, getJedis());
  }

  public io.rebloom.client.Client clientForBloom() {
    return new io.rebloom.client.Client(getJedis());
  }
  
  public RedisAI clientForAI() {
    return new RedisAI(getJedis());
  }

  private Jedis getJedis() {
    return (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
  }

  private JedisConnectionFactory jedisConnectionFactory;
}
