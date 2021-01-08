package com.bootdo.clouddoexam.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoexam.domain.BlogCommentsDO;
import com.bootdo.clouddoexam.service.BlogCommentsService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-09 14:50:19
 */

@RestController
@RequestMapping("/blogComments")
public class BlogCommentsController {
	@Autowired
	private BlogCommentsService blogCommentsService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BlogCommentsDO> blogCommentsList = blogCommentsService.list(query);
		int total = blogCommentsService.count(query);
		PageUtils pageUtils = new PageUtils(blogCommentsList, total);
		return R.ok().put("page",pageUtils);
	}
	@GetMapping("/listbyblogid")//根据博客id查看评论
	public R listbyblogid(long id){
		//查询列表数据
		System.out.println(">>>>>>>"+"测试博客评论");
		List<BlogCommentsDO> blogCommentsList = blogCommentsService.listbyblogid(id);

		return R.ok().put("data",blogCommentsList);
	}
	@GetMapping("/Commentsblogid")//根据博客id查看评论
	public void Commentsblogid(@RequestParam Map<String, Object> params){
		//查询列表数据
		System.out.println(">>>>>>>"+"测试博客评论");
		BlogCommentsDO blogComments=new  BlogCommentsDO();
		blogComments.setContent(params.get("content").toString());
		blogComments.setTopicId(params.get("topicId").toString());
	    blogCommentsService.saveComments(blogComments);


	}
	@GetMapping("/add")
	String add(){
	    return "clouddoexam/blogComments/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Integer id,Model model){
		BlogCommentsDO blogComments = blogCommentsService.get(id);
		model.addAttribute("blogComments", blogComments);
	    return "clouddoexam/blogComments/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  BlogCommentsDO blogComments){
		if(blogCommentsService.save(blogComments)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  BlogCommentsDO blogComments){
		blogCommentsService.update(blogComments);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(blogCommentsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		blogCommentsService.batchRemove(ids);
		return R.ok();
	}
	
}
