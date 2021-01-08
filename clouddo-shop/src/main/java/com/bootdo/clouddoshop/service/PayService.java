package com.bootdo.clouddoshop.service;

import com.bootdo.clouddocommon.intercepter.FeignIntercepter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@FeignClient(name = "api-pay", configuration = FeignIntercepter.class)
public interface PayService {
    @GetMapping("/pay/LoadPayQRCode")
   String LoadPayQRCode() throws IOException;
}
