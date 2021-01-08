package com.bootdo.clouddozuul.prc.activiti;

import com.bootdo.clouddocommon.intercepter.FeignIntercepter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FeignClient(name = "api-activiti", configuration = FeignIntercepter.class)
public interface ActivitiService {
    @GetMapping("/task/querytaskimage")//客户端多参数解决方法
    void readResource(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("pProcessInstanceId")String pProcessInstanceId, HttpServletResponse response) throws Exception;
}
