package com.xgq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private StringRedisTemplate redisTemplate;

    @GetMapping("set/{key}/{value}")
    @ApiOperation(value="设置缓存")
    public String set(@PathVariable("key")String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return key + "," + value;
    }

    @GetMapping("get/{key}")
    @ApiOperation(value="根据key获取缓存")
    public String get(@PathVariable("key") String key) {

        return "key=" + key + ",value=" + redisTemplate.opsForValue().get(key);
    }
}
