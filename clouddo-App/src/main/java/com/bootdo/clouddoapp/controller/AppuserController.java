package com.bootdo.clouddoapp.controller;

import java.util.List;
import java.util.Map;


import com.bootdo.clouddoapp.domain.AppuserDO;
import com.bootdo.clouddoapp.service.AppuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-06-24 16:58:14
 */

@RestController
@RequestMapping("/appuser")
public class AppuserController {
	@Autowired
	private AppuserService appuserService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AppuserDO> appuserList = appuserService.list(query);
		int total = appuserService.count(query);
		PageUtils pageUtils = new PageUtils(appuserList, total);
		return R.ok().put("page",pageUtils);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/appuser/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Integer id,Model model){
		AppuserDO appuser = appuserService.get(id);
		model.addAttribute("appuser", appuser);
	    return "clouddoexam/appuser/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  AppuserDO appuser){
		if(appuserService.save(appuser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  AppuserDO appuser){
		appuserService.update(appuser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(appuserService.remove(id)>0){
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
		appuserService.batchRemove(ids);
		return R.ok();
	}
	
}
