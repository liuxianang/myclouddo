package com.bootdo.clouddoRabbitMQ.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Girl {


    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Min(value = 18, message = "未成年禁止入内")
    private int age;

    public Girl() {
    }
}