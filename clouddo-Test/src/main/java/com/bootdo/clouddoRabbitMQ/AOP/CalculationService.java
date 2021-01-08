package com.bootdo.clouddoRabbitMQ.AOP;

public interface CalculationService {
    /**
     * 加法运算
     * @param x
     * @param y
     * @return
     */
    public Integer add(Integer x, Integer y);
}