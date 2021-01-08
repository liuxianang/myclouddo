package com.bootdo.clouddoRabbitMQ.AOP;

import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Override
    public Integer add(Integer x, Integer y) {
        if(x==null||y==null){
            throw  new NullPointerException("参数不能为空");
        }
        return x+y;
    }
}