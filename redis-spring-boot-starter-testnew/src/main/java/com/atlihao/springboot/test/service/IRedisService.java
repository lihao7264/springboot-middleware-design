package com.atlihao.springboot.test.service;


import com.atlihao.springboot.redis.annotation.XRedis;

@XRedis
public interface IRedisService {

    String get(String key);

    void set(String key, String val);

}
