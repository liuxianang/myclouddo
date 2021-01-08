package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.domain.RoleDO;
import com.bootdo.clouddoadmin.domain.TaskDO;
import com.bootdo.clouddoadmin.quartz.QuartzManager;
import com.bootdo.clouddoadmin.service.JobService;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bootdo
 * 角色
 */
@RequestMapping("/job")
@RestController
public class JobController {
    @Autowired
    RoleService roleService;
    @Autowired
    private JobService taskScheduleJobService;
    @Autowired
    private QuartzManager quartzManager;
    @GetMapping()
    R list(@RequestParam Map<String, Object> params) {
        System.out.println("assssssssssssssssssssssssssss");
        // 查询列表数据
        Query query = new Query(params);
        List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(query);
        int total = taskScheduleJobService.count(query);
        PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
        return R.ok().put("page",pageUtils);
    }

    @GetMapping("/userId/{userId}")
    List<Long> roleIdByUserId(@PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    @PostMapping
    R save(@RequestBody TaskDO taskDO){
        System.out.println("asdsadasdsadsadasdsasdasdsa");
        if(taskScheduleJobService.save(taskDO)>0){
            return R.ok();
        }
        return R.error();
    }

    @PutMapping
    R update(@RequestBody TaskDO taskDO){
        System.out.println(">>>>>>>>>>");
        if(taskScheduleJobService.update(taskDO)>0){
            return R.ok();
        }
        return R.error();
    }
    @DeleteMapping()
    R remove( Long id) {
        System.out.println(">>>>>>>>>>"+id);
        return R.operate (taskScheduleJobService.remove(id) > 0);
    }
    @GetMapping(value = "/changeJobStatus")
    @ResponseBody
    public R changeJobStatus(Long id,String cmd ) throws SchedulerException {
        String label = "停止";
        if ("start".equals(cmd)) {
            label = "启动";
        } else {
            label = "停止";
        }
        try {
            taskScheduleJobService.changeStatus(id, cmd);
            return R.ok("任务" + label + "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("任务" + label + "失败");
    }

}

