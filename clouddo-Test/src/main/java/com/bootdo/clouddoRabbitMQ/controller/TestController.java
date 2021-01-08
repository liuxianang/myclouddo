package com.bootdo.clouddoRabbitMQ.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("/pay")
public class TestController {
    @GetMapping("1111")
    public String LoadPay() throws IOException {
        return "33333";
    }
}
