package com.bootdo.clouddocommon.dto;

import java.io.Serializable;

public class ServiceDo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Application;

    private String AMIs;

    private String AvailabilityZones;
    private String Status;

    public ServiceDo() {
    }



    public String getAMIs() {
        return AMIs;
    }

    public void setAMIs(String AMIs) {
        this.AMIs = AMIs;
    }

    public String getAvailabilityZones() {
        return AvailabilityZones;
    }

    public void setAvailabilityZones(String availabilityZones) {
        AvailabilityZones = availabilityZones;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getApplication() {
        return Application;
    }

    public void setApplication(String application) {
        Application = application;
    }
}
