package com.haohao.demo.redis.subscribe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlueDogSubscribe implements RedisSubscribe {

    /**
     * 订阅处理消息接口
     *
     * @param message 消息
     */
    @Override
    public void receiveMessage(String message) {
        try {
            Thread.sleep(3000);

            log.info("BlueDogSubscribe message:{}", message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
