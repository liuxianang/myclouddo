package com.bootdo.clouddoRabbitMQ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitmq生产者示例
 *
 * @Author YUBIN
 */
@Component // 将该类交给Spring管理
public class RabbitMqProducerDemo {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqProducerDemo.class);

    @Autowired // 注入rabbitmq 模板
    private AmqpTemplate rabbitTemplate;

    /**
     * 发送消息的方法 hello-queue
     */
    public void sendHelloMessage() {
        // 定义消息体
        String message = "RabbitMqProducerDemo hello queue send message";
        rabbitTemplate.convertAndSend("hello-queue",message);
        logger.info("==================RabbitMqProducerDemo hello queue send message success");
    }
    public void sendtest() {
        // 定义消息体
        String message = "我在测试java-RabbitMQ";
        rabbitTemplate.convertAndSend("test",message);
        logger.info("==================RabbitMqProducerDemo hello queue send message success");
    }
    /**
     * 发送消息的方法 user-queue
     */
    public void sendUserMessage() {
        // 定义消息体
        String message = "RabbitMqProducerDemo user queue send message";
        rabbitTemplate.convertAndSend("user-queue","我是大神我在测试");
        logger.info("===================RabbitMqProducerDemo user queue send message success");
    }
}