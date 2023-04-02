package com.haohao.demo.redis;

import cn.hutool.core.util.RandomUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class Test {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @org.junit.jupiter.api.Test
    void test() {
        for (int i = 0; i < 100; i++) {
            stringRedisTemplate.convertAndSend("cat", "cat" + i);
            stringRedisTemplate.convertAndSend("dog", "dog" + i);
        }
    }
}
