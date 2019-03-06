package com.xgq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Auther: HeJD
 * @Date: 2018/9/17 17:18
 * @Description:
 */
@Api(tags = "redis测试接口，默认key-value模式")
@RestController
@RequestMapping("/redis/")
public class RedisController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate cacheRedisTemplate;


    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private JedisCluster jedisCluster;

    @GetMapping("set-string/{key}/{value}")
    @ApiOperation(value="StringRedisTemplate设置缓存")
    public String setString(@PathVariable("key")String key, @PathVariable("value") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }

    @GetMapping("set-obj/{key}/{value}")
    @ApiOperation(value="RedisTemplate设置缓存")
    public String setObj(@PathVariable("key")String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }
    @GetMapping("set-cluster/{key}/{value}")
    @ApiOperation(value="cacheRedisTemplate设置集群缓存")
    public String setCluster(@PathVariable("key")String key, @PathVariable("value") String value) {

        cacheRedisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }

    @GetMapping("get/{key}")
    @ApiOperation(value="根据key获取缓存")
    public String get(@PathVariable("key") String key) {

        return "key=" + key + ",value=" + redisTemplate.opsForValue().get(key);
    }
}
