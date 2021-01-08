package com.bootdo.clouddoshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableFeignClients
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class ClouddoShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouddoShopApplication.class, args);
    }

}
