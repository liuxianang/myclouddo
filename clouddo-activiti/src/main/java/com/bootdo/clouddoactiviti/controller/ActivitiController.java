package com.bootdo.clouddoactiviti.controller;



import com.bootdo.clouddoactiviti.admin.MenuService;
import com.bootdo.clouddoactiviti.vo.ActivitiDo;
import com.bootdo.clouddocommon.constants.CommonConstants;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.UserToken;
import com.bootdo.clouddocommon.utils.JwtUtils;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.R;
import org.activiti.engine.*;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import static java.lang.System.out;

@RestController
@RequestMapping("/Activiti")
public class ActivitiController {
    @Autowired
    MenuService menuService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @GetMapping("/deploy")
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()//创建一个部署的构建器
                .addClasspathResource("bx.bpmn")//从类路径中添加资源,一次只能添加一个资源
                .name("报销流程")//设置部署的名称
                .category("办公类别")//设置部署的类别
                .deploy();


    }
    @GetMapping("/list")
    R list(@RequestParam Map<String, Object> params) {

        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();

        List<ActivitiDo> mylist = new ArrayList<ActivitiDo>();
        for (int i = 0; i < list.size(); i++) {
            ActivitiDo p = new ActivitiDo();
            p.setDeploymentId(list.get(i).getDeploymentId());
            p.setId(list.get(i).getId());
            p.setKey(list.get(i).getKey());
            p.setName(list.get(i).getName());
            p.setResourceName(list.get(i).getResourceName());
            p.setDiagramresourcename(list.get(i).getDiagramResourceName());
            p.setDeploymentName(repositoryService.createDeploymentQuery().deploymentId(list.get(i).getDeploymentId()).singleResult().getName());
            mylist.add(p);
        }
        int total = mylist.size();
        int start=Integer.parseInt(params.get("limit").toString())*(Integer.parseInt(params.get("page").toString())-1);
        int end=Integer.parseInt(params.get("limit").toString())*(Integer.parseInt(params.get("page").toString()));
        if(end>total){
            end=total;
        }
        PageUtils pageUtils = new PageUtils(mylist.subList(start, end), total);
        return R.ok().put("data",pageUtils);
    }
    @GetMapping("/deletedeploy")
    public R deletedeploy(@RequestParam Map<String, Object> params) throws Exception {
        repositoryService.deleteDeployment(params.get("id").toString(), true);
        return R.ok();
    }

    @GetMapping("processDefinitionImage")
    public void processDefinitionImage(String processDefinitionId, HttpServletResponse response) throws IOException {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        OutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();
    }
    @GetMapping("/startProcess")
    R startProcess(@RequestParam Map<String, Object> params,HttpServletRequest request) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>"+request.getHeader(CommonConstants.CONTEXT_TOKEN));
        UserToken user= JwtUtils.getInfoFromToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));

        // 创建流程变量
        Map<String,Object> variables = new HashMap<String,Object>();
        variables.put("userid", user.getUserId());
        //取得流程实例请假
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(params.get("processDefiKey").toString(),variables );//通过流程定义的key 来执行流程
        return  R.ok("新建流程成功");
    }
    @GetMapping({"/test2"})
    String test2(HttpServletRequest request)  {

        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.test();
    }

}
