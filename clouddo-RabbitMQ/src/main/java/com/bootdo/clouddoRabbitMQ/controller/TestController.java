package com.bootdo.clouddoRabbitMQ.controller;



import com.bootdo.clouddocommon.context.FilterContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("/pay")
public class TestController {
    @Autowired
    private RabbitMqProducerDemo producerDemo;
    @Autowired
    private RabbitMqConsumerDemo consumerDemo;

    @GetMapping("1111")
    public String LoadPay() throws IOException {
        System.out.println("获取的token"+FilterContextHandler.getToken());
        producerDemo.sendHelloMessage();
        producerDemo.sendUserMessage();
        producerDemo.sendtest();
        return "11111";
    }

    @GetMapping("2222")
    public String LoadPay222() throws IOException {
   System.out.println();
        consumerDemo.executetest();
        return "11111";
    }
}
