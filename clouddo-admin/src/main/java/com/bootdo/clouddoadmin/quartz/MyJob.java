package com.bootdo.clouddoadmin.quartz;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
public class MyJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            executeTask();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    private void executeTask() throws SchedulerException {
        System.out.println("定时任务*************");
    }
}