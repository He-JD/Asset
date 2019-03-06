package com.xgq;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: he_jd
 * @Description:
 * @Date: 2019-03-02
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClusterTest {


    @Autowired
    private RedisTemplate redisTemplate;


    public void testRedisCluster(){
        redisTemplate.opsForValue().set("name","tom");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }



}
