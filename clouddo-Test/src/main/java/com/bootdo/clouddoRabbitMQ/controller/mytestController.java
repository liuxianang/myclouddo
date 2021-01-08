package com.bootdo.clouddoRabbitMQ.controller;

import com.alibaba.fastjson.JSON;

import com.bootdo.clouddoRabbitMQ.domain.Girl;
import com.bootdo.clouddoRabbitMQ.service.RedisService;
import com.bootdo.clouddoRabbitMQ.value.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/pay")
public class mytestController {
    @Autowired // 通过注解直接注入配置类
    private AuthorSettings authorSettings;
    @Resource
    private RedisService redisService;
    @GetMapping("/test")
    public String helloWorld() {
        redisService.set("maomao", "hello");
        System.out.println(redisService.get("maomao"));
        return "Hello World!  " + authorSettings.getName() + "::" + authorSettings.getAge();
    }
    @GetMapping("/girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return  null;
        }
        return  null;
    }
}
