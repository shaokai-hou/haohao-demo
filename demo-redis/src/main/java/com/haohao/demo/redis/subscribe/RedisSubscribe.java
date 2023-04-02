package com.haohao.demo.redis.subscribe;

import org.springframework.stereotype.Component;

@Component
public interface RedisSubscribe {

    /**
     * 订阅处理消息接口
     *
     * @param message 消息
     */
    void receiveMessage(String message);
}
