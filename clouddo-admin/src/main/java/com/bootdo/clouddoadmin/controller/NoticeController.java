package com.bootdo.clouddoadmin.controller;

import java.util.List;
import java.util.Map;


import com.bootdo.clouddoadmin.domain.NoticeDO;
import com.bootdo.clouddoadmin.service.NoticeService;
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
 * @date 2020-05-29 17:22:49
 */

@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NoticeDO> noticeList = noticeService.list(query);
		int total = noticeService.count(query);
		PageUtils pageUtils = new PageUtils(noticeList, total);
		return R.ok().put("page",pageUtils);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/notice/add";
	}

	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Integer id,Model model){
		NoticeDO notice = noticeService.get(id);
		model.addAttribute("notice", notice);
	    return "clouddoexam/notice/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  NoticeDO notice){
		if(noticeService.save(notice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  NoticeDO notice){
		noticeService.update(notice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(noticeService.remove(id)>0){
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
		noticeService.batchRemove(ids);
		return R.ok();
	}
	
}
