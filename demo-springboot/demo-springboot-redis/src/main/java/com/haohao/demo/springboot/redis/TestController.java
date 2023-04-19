package com.haohao.demo.springboot.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/a")
    public void test() {
        for (int i = 0; i < 5000; i++) {
            stringRedisTemplate.convertAndSend("cat", "cat" + i);
            stringRedisTemplate.convertAndSend("dog", "dog" + i);
        }
    }
}
