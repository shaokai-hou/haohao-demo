package com.haohao.demo.springboot.redis.subscribe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedDogSubscribe implements RedisSubscribe {

    /**
     * 订阅处理消息接口
     *
     * @param message 消息
     */
    @Override
    public void receiveMessage(String message) {
        log.info("RedDogSubscribe message:{}", message);
    }
}
