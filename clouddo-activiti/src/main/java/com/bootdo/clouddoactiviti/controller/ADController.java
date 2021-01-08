package com.bootdo.clouddoactiviti.controller;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class ADController {
    @GetMapping("/deploy")
    public void deploy() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取仓库服务 ：管理流程定义
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()//创建一个部署的构建器
                .addClasspathResource("bx.bpmn")//从类路径中添加资源,一次只能添加一个资源
                .name("报销流程")//设置部署的名称
                .category("办公类别")//设置部署的类别
                .deploy();

        System.out.println("部署的id"+deploy.getId());
        System.out.println("部署的名称"+deploy.getName());
    }
    @GetMapping("/startProcess")
    public void startProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //指定执行我们刚才部署的工作流程
        String processDefiKey="demo2";
        //取运行时服务
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 创建流程变量
        Map<String,Object> variables = new HashMap<String,Object>();
        variables.put("流程名", "请假");
        //取得流程实例请假
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefiKey,variables );//通过流程定义的key 来执行流程
        System.out.println("流程实例id:"+pi.getId());//流程实例id
        System.out.println("流程定义id:"+pi.getProcessDefinitionId());//输出流程定义的id
    }
    @GetMapping("/queryTask")
    public void queryTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //任务的办理人
        String assignee="";
        //取得任务服务
        TaskService taskService = processEngine.getTaskService();
        //创建一个任务查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        //办理人的任务列表
        List<Task> list = taskQuery.deploymentId("5001")//指定办理人
                .list();
        //遍历任务列表
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println("任务的办理人："+task.getAssignee());
                System.out.println("任务的id："+task.getId());
                System.out.println("任务的名称："+task.getName());
            }
        }
    }
    @GetMapping("/compileTask")
    public void compileTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskId="10002";
        //taskId：任务id
        processEngine.getTaskService().complete(taskId);
        System.out.println("当前任务执行完毕");
    }
    @GetMapping("/queryProcessDefination")
    public void queryProcessDefination(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String processDefiKey="buyBill";//流程定义key
        //获取流程定义列表
        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery()
                //查询 ，好比where
//        .processDefinitionId(proDefiId) //流程定义id
                // 流程定义id  ： buyBill:2:704   组成 ： proDefikey（流程定义key）+version(版本)+自动生成id
                .processDefinitionKey(processDefiKey)//流程定义key 由bpmn 的 process 的  id属性决定
//        .processDefinitionName(name)//流程定义名称  由bpmn 的 process 的  name属性决定
//        .processDefinitionVersion(version)//流程定义的版本
                .latestVersion()//最新版本

                //排序
                .orderByProcessDefinitionVersion().desc()//按版本的降序排序

                //结果
//        .count()//统计结果
//        .listPage(arg0, arg1)//分页查询
                .list();


        //遍历结果
        if(list!=null&&list.size()>0){
            for(ProcessDefinition temp:list){
                System.out.print("流程定义的id: "+temp.getId());
                System.out.print("流程定义的key: "+temp.getKey());
                System.out.print("流程定义的版本: "+temp.getVersion());
                System.out.print("流程定义部署的id: "+temp.getDeploymentId());
                System.out.println("流程定义的名称: "+temp.getName());
            }
        }
    }
    @GetMapping("/deleteProcessDefi")
    public void deleteProcessDefi(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //通过部署id来删除流程定义
        String deploymentId="101";
        processEngine.getRepositoryService().deleteDeployment(deploymentId);
    }
    @GetMapping("/getProcessInstanceState")
    public void getProcessInstanceState(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String processInstanceId="605";
        ProcessInstance pi = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();//返回的数据要么是单行，要么是空 ，其他情况报错
        //判断流程实例的状态
        if(pi!=null){
            System.out.println("该流程实例"+processInstanceId+"正在运行...  "+"当前活动的任务:"+pi.getActivityId());
        }else{
            System.out.println("当前的流程实例"+processInstanceId+" 已经结束！");
        }

    }
    @GetMapping("/queryHistoryProcInst")
    public void queryHistoryProcInst(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<HistoricProcessInstance> list = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .list();
        if(list!=null&&list.size()>0){
            for(HistoricProcessInstance temp:list){
                System.out.println("历史流程实例id:"+temp.getId());
                System.out.println("历史流程定义的id:"+temp.getProcessDefinitionId());
                System.out.println("历史流程实例开始时间--结束时间:"+temp.getStartTime()+"-->"+temp.getEndTime());
            }
        }
    }
    @GetMapping("/queryHistoryTask")
    public void queryHistoryTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String processInstanceId="605";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        if(list!=null&&list.size()>0){
            for(HistoricTaskInstance temp:list){
                System.out.print("历史流程实例任务id:"+temp.getId());
                System.out.print("历史流程定义的id:"+temp.getProcessDefinitionId());
                System.out.print("历史流程实例任务名称:"+temp.getName());
                System.out.println("历史流程实例任务处理人:"+temp.getAssignee());
            }
        }
    }
}
