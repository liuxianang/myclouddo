package com.bootdo.clouddozuul.prc.rabbitMQ;

import com.bootdo.clouddocommon.dto.MenuDTO;
import com.bootdo.clouddocommon.intercepter.FeignIntercepter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "api-rabbitmq", configuration = FeignIntercepter.class)
public interface MqService {
    @GetMapping("/pay/1111")
   String LoadPay() throws IOException;
}
