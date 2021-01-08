package com.bootdo.clouddoapp.controller;

import java.util.List;
import java.util.Map;


import com.bootdo.clouddoapp.domain.imageDTO;
import com.bootdo.clouddoapp.service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-06-24 16:58:08
 */

@RestController
@RequestMapping("/appimage")
public class AppimageController {
	@Autowired
	private imageService appimageService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<imageDTO> appimageList = appimageService.list(query);
		int total = appimageService.count(query);
		PageUtils pageUtils = new PageUtils(appimageList, total);
		return R.ok().put("page",pageUtils);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/appimage/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Long id,Model model){
		imageDTO appimage = appimageService.get(id);
		model.addAttribute("appimage", appimage);
	    return "clouddoexam/appimage/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  imageDTO appimage){
		if(appimageService.save(appimage)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  imageDTO appimage){
		appimageService.update(appimage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Long id){
		if(appimageService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		appimageService.batchRemove(ids);
		return R.ok();
	}
	
}
