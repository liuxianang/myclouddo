package com.bootdo.clouddoapp.controller;

import java.util.List;
import java.util.Map;


import com.bootdo.clouddoapp.domain.AppshoppingcarDO;
import com.bootdo.clouddoapp.service.AppshoppingcarService;
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
 * @date 2020-06-24 16:58:11
 */

@RestController
@RequestMapping("/appshoppingcar")
public class AppshoppingcarController {
	@Autowired
	private AppshoppingcarService appshoppingcarService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AppshoppingcarDO> appshoppingcarList = appshoppingcarService.list(query);
		int total = appshoppingcarService.count(query);
		PageUtils pageUtils = new PageUtils(appshoppingcarList, total);
		return R.ok().put("page",pageUtils);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/appshoppingcar/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") String id,Model model){
		AppshoppingcarDO appshoppingcar = appshoppingcarService.get(id);
		model.addAttribute("appshoppingcar", appshoppingcar);
	    return "clouddoexam/appshoppingcar/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  AppshoppingcarDO appshoppingcar){
		if(appshoppingcarService.save(appshoppingcar)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  AppshoppingcarDO appshoppingcar){
		appshoppingcarService.update(appshoppingcar);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( String id){
		if(appshoppingcarService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] ids){
		appshoppingcarService.batchRemove(ids);
		return R.ok();
	}
	
}
