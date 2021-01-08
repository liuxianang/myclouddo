package com.bootdo.clouddoRabbitMQ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMq消费者示例
 *
 * @Author YUBIN
 * @create 2018-08-08
 */
@Component
//@RabbitListener(queues = {"hello-queue","user-queue"})
public class RabbitMqConsumerDemo {
    @Autowired // 注入rabbitmq 模板
    private AmqpTemplate rabbitTemplate;
    private static Logger logger = LoggerFactory.getLogger(RabbitMqConsumerDemo.class);

    //@RabbitHandler
    @RabbitListener(queues = "hello-queue")
    public void executeHello(String message) {
        logger.info("executeHello================接收到的消息是:" + message);
    }

    @RabbitListener(queues = "user-queue")
    public void executeUser(String message) {

        logger.info("executeUser=================接收到的消息是:" + message);
    }
    public void executetest() {
        Message aaa = rabbitTemplate.receive("test");
        logger.info("executeUser=================接收到的消息是:" +aaa.getBody());
        System.out.println(">>>>>>>>>"+aaa);
    }
}