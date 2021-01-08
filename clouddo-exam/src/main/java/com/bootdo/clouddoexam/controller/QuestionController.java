package com.bootdo.clouddoexam.controller;

import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.bootdo.clouddoexam.domain.treeQus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoexam.domain.QuestionDO;
import com.bootdo.clouddoexam.service.QuestionService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-16 16:07:43
 */

@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QuestionDO> questionList = questionService.list(query);
		int total = questionService.count(query);
		PageUtils pageUtils = new PageUtils(questionList, total);
		JSONObject jsonObject = new JSONObject();  //创建Json对象
		List<treeQus> tree=questionService.findtree();
		jsonObject.put("title","科目");
		jsonObject.put("key","");
		jsonObject.put("children",tree);
		return R.ok().put("page",pageUtils).put("tree",jsonObject);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/question/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Integer id,Model model){
		QuestionDO question = questionService.get(id);
		model.addAttribute("question", question);
	    return "clouddoexam/question/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  QuestionDO question){
		if(questionService.save(question)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  QuestionDO question){
		questionService.update(question);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(questionService.remove(id)>0){
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
		questionService.batchRemove(ids);
		return R.ok();
	}
	
}
