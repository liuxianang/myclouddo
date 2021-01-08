package com.bootdo.clouddoactiviti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableFeignClients
@EnableCaching
@EnableScheduling
@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ClouddoActivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClouddoActivitiApplication.class, args);
    }

}
