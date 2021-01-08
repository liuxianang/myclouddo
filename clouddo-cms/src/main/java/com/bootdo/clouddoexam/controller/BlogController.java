package com.bootdo.clouddoexam.controller;


import java.util.List;
import java.util.Map;


import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddoexam.domain.BlogDO;
import com.bootdo.clouddoexam.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;


/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-12 12:17:36
 */

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("{id}")
    public R get(@PathVariable Long id) {
        BlogDO blog = blogService.get(id);
        return R.data(blog);
    }
    @GetMapping("blogall")
    public R blogall() {
        blogService.listall();
        System.out.println(">>>>>>>"+"测试跨域"+ blogService.listall());
        return R.ok().put("data", blogService.listall());
    }
    @GetMapping("blogid")
    public R blogid(long id) {

        System.out.println(">>>>>>>"+"测试"+blogService.get(id));
        return R.ok().put("data",blogService.get(id));
    }
    @GetMapping("blogidweiguan")
    public R blogidweiguan(long id) {

        System.out.println(">>>>>>>"+"围观"+blogService.listallweiguan());
        return R.ok().put("data",blogService.listallweiguan());
    }

    /**
     * 分页查询
     */
    @GetMapping
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<BlogDO> fileList = blogService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbb"+params);
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
