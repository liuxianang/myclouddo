package com.bootdo.clouddoexam.controller;


import com.bootdo.clouddoexam.domain.BlogDO;
import com.bootdo.clouddoexam.service.BlogService;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-12 12:17:36
 */

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private BlogService blogService;

    @GetMapping("{id}")
    public R get(@PathVariable Long id) {
        BlogDO blog = blogService.get(id);
        return R.data(blog);
    }



    /**
     * 分页查询
     */
    @GetMapping
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<BlogDO> fileList = blogService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        System.out.println("wo>>>>>>>>>>"+params);
        int total = blogService.count(query);
//        PageUtils pageUtils = new PageUtils(fileDTOS, total);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return R.page(pageUtils);
    }

    /**
     * 保存
     */
    @PostMapping
    public R save(BlogDO file) {
        return R.operate(blogService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(BlogDO file) {
        return R.operate(blogService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public R remove(Long id) {
        System.out.println("传入的删除id"+id);
        System.out.println("dddddddddddddddddddddd");
        return R.operate(blogService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        return R.operate(blogService.batchRemove(ids) > 0);
    }
}
