package com.haohao.demo.springboot.redis;

import com.haohao.demo.springboot.redis.subscribe.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter(new CatSubscribe()), new PatternTopic("cat"));
        container.addMessageListener(listenerAdapter(new YellowDogSubscribe()), new PatternTopic("dog"));
        container.addMessageListener(listenerAdapter(new RedDogSubscribe()), new PatternTopic("dog"));
        container.addMessageListener(listenerAdapter(new BlueDogSubscribe()), new PatternTopic("dog"));
        return container;
    }

    /*
     *  prototype表示每次获得bean都会生成一个新的对象
     */
    @Bean
    @Scope("prototype")
    MessageListenerAdapter listenerAdapter(RedisSubscribe redisSubscribe) {
        return new MessageListenerAdapter(redisSubscribe, "receiveMessage");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // key序列化
        template.setKeySerializer(new StringRedisSerializer());
        // value序列化
        template.setValueSerializer(jsonRedisSerializer);
        return template;
    }
}
