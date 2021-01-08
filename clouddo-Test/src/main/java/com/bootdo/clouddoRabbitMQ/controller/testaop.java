package com.bootdo.clouddoRabbitMQ.controller;

import com.bootdo.clouddoRabbitMQ.AOP.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class testaop {
    @Autowired
   private CalculationService calculationService;
    @RequestMapping("/testaop")
    @ResponseBody
    public Integer testaop(Integer x,Integer y){
        Integer result = calculationService.add(x, y);
        return result;
    }
}
