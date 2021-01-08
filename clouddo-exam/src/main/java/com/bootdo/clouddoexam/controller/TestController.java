package com.bootdo.clouddoexam.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoexam.domain.TestDO;
import com.bootdo.clouddoexam.service.TestService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-14 13:56:02
 */

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private TestService testService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestDO> testList = testService.list(query);
		int total = testService.count(query);
		PageUtils pageUtils = new PageUtils(testList, total);
		return R.ok().put("page",pageUtils);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/test/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Integer id,Model model){
		TestDO test = testService.get(id);
		model.addAttribute("test", test);
	    return "clouddoexam/test/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  TestDO test){

		if(testService.save(test)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  TestDO test){
		testService.update(test);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(testService.remove(id)>0){
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
		testService.batchRemove(ids);
		return R.ok();
	}
	
}
