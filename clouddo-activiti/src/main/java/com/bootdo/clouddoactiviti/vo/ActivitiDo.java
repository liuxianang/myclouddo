package com.bootdo.clouddoactiviti.vo;

import java.io.Serializable;

public class ActivitiDo implements Serializable {
    private static final long serialVersionUID = 1L;


    private String deploymentId;

    private String id;

    private String key;

    private String name;

    private String resourceName;
    private String deploymentName;
    private String diagramresourcename;




    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDiagramresourcename() {
        return diagramresourcename;
    }

    public void setDiagramresourcename(String diagramresourcename) {
        this.diagramresourcename = diagramresourcename;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeploymentName() {
        return deploymentName;
    }

    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName;
    }
}
