package com.bootdo.clouddoRabbitMQ.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq的相关配置
 *
 * @Author YUBIN
 */
@Configuration // 相当于xml配置文件
public class RabbitMqConfiguration {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello-queue");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user-queue");
    }
    @Bean
    public Queue test() {
        return new Queue("test");
    }
}