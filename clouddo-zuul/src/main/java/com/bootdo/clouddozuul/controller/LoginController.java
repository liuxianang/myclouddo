package com.bootdo.clouddozuul.controller;

import com.bootdo.clouddocommon.constants.CommonConstants;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.MenuDTO;
import com.bootdo.clouddozuul.prc.activiti.ActivitiService;
import com.bootdo.clouddozuul.prc.admin.MenuService;
import com.bootdo.clouddozuul.prc.rabbitMQ.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @version V1.0
 */
@RestController
public class LoginController {
    @Autowired
    MenuService menuService;
    @Autowired
    MqService mqService;
    @Autowired
    ActivitiService activitiService;
    @GetMapping({"/test"})
    List<MenuDTO> login(HttpServletRequest request)  {
        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));

        return menuService.userMenus();
    }
   /* 微服务之间方法调用*/
    @GetMapping({"/test3"})
   String test2(HttpServletRequest request)  {

        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.test();
    }
    @GetMapping({"/test2"})
    String test3(HttpServletRequest request) throws IOException {

       FilterContextHandler.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImNvbnRleHRVc2VySWQiOiIxIiwiY29udGV4dE5hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJjb250ZXh0cm9sZU5hbWUiOiLnrqHnkIblkZjop5LoibIiLCJyZW5ld2FsVGltZSI6MTU2OTc0MDQ1MTc4MCwiZXhwIjoxNTY5NzQ0MDUxfQ.3_qsaKjMJesntKCrFVbiEWsQR6mZliPC8_WPwyDE5F4");
        System.out.println("设置的token"+FilterContextHandler.getToken());
        return mqService.LoadPay();
    }
    @GetMapping({"/test4"})
     void test4(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("pProcessInstanceId")String pProcessInstanceId, HttpServletResponse response) throws Exception {

        //FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
         activitiService.readResource(processDefinitionId, pProcessInstanceId,response);
    }
}
