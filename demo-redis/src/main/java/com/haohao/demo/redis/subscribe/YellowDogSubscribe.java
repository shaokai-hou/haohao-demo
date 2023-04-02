package com.haohao.demo.redis.subscribe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class YellowDogSubscribe implements RedisSubscribe {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * 订阅处理消息接口
     *
     * @param message 消息
     */
    @Override
    public void receiveMessage(String message) {
        threadPool.submit(() -> log.info("YellowDogSubscribe message:{}", message));
    }
}
