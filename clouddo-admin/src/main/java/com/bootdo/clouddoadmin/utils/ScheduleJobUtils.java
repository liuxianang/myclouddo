package com.bootdo.clouddoadmin.utils;


import com.bootdo.clouddoadmin.domain.ScheduleJob;
import com.bootdo.clouddoadmin.domain.TaskDO;

public class ScheduleJobUtils {
    public static ScheduleJob entityToData(TaskDO scheduleJobEntity) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
        scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
        scheduleJob.setDescription(scheduleJobEntity.getDescription());
        scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
        scheduleJob.setJobName(scheduleJobEntity.getJobName());
        scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
        scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
        scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
        scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
        return scheduleJob;
    }
}
