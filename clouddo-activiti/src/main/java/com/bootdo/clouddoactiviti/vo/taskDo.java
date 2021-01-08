package com.bootdo.clouddoactiviti.vo;

import java.io.Serializable;

public class taskDo implements Serializable {
    private static final long serialVersionUID = 1L;


    private String id;
    private String name;
    private String creater;
    private String processDefinitionId;
    private String pProcessInstanceId;
    private String resourceName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getpProcessInstanceId() {
        return pProcessInstanceId;
    }

    public void setpProcessInstanceId(String pProcessInstanceId) {
        this.pProcessInstanceId = pProcessInstanceId;
    }
}
