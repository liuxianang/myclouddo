package com.bootdo.clouddoexam.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.bootdo.clouddoexam.domain.QuestionDO;
import com.bootdo.clouddoexam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoexam.domain.PaperDO;
import com.bootdo.clouddoexam.service.PaperService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-17 09:34:01
 */

@RestController
@RequestMapping("/paper")
public class PaperController {
	@Autowired
	private PaperService paperService;
	@Autowired
	private QuestionService questionService;
	@GetMapping
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaperDO> paperList = paperService.list(query);
		int total = paperService.count(query);
		PageUtils pageUtils = new PageUtils(paperList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	String add(){
	    return "clouddoexam/paper/add";
	}
	@GetMapping("/edit/{id}")

	String edit(@PathVariable("id") Integer id,Model model){
		PaperDO paper = paperService.get(id);
		model.addAttribute("paper", paper);
	    return "clouddoexam/paper/edit";
	}
	/*查询题目组装试卷*/
	@GetMapping("/Questionall")
	R Questionall(@RequestParam Map<String, Object> params){

		List<QuestionDO> list = questionService.findall();
		Map map1 = new HashMap();  //创建map对象
		Map map2 = new HashMap();  //创建map对象
		Map map3 = new HashMap();  //创建map对象

		List<Map> listall= new ArrayList<Map>();
		List<Map> list1= new ArrayList<Map>();
		List<Map> list2= new ArrayList<Map>();
		List<Map> list3= new ArrayList<Map>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getType().equals("0")){/*单选题*/
				list.get(i).setAnswer("");
				Map map = new HashMap();
				map.put("key",list.get(i).getId());
				map.put("label",list.get(i).getTitle());
				list1.add(map);
			}
			else if(list.get(i).getType().equals("1")){/*多选题*/
				String[] ary={};
				list.get(i).setAnswerduo(ary);
				Map map = new HashMap();
				map.put("id",list.get(i).getId());
				map.put("label",list.get(i).getTitle());
				list2.add(map);
			}
			else if(list.get(i).getType().equals("2")){/*简答题*/
				list.get(i).setAnswer("");
				Map map = new HashMap();
				map.put("id",list.get(i).getId());
				map.put("label",list.get(i).getTitle());
				list3.add(map);

			}
		}
		map1.put("id",10001);
		map1.put("label","单选题");
		map1.put("children",list1);
		map2.put("id",20001);
		map2.put("label","多选题");
		map2.put("children",list2);
		map3.put("id",30001);
		map3.put("label","简答题");
		map3.put("children",list3);
		listall.add(map1);
		listall.add(map2);
		listall.add(map3);
		String[] checkedkeys;
		Integer[] ids;
		String checkedkey = paperService.get(Integer.parseInt(params.get("id").toString())).getQuestionallId();
		if(!checkedkey.equals("")){
			checkedkeys =checkedkey.split(",");
			ids = new Integer[checkedkeys.length];
			for(int i=0;i<checkedkeys.length;i++){

				ids[i] = Integer.parseInt(checkedkeys[i]);

			}
		}else{
			ids=null;
		}


		return R.ok().put("exam",listall).put("checkedkey",ids);
	}
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  PaperDO paper){
		if(paperService.save(paper)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  PaperDO paper){
		paperService.update(paper);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(paperService.remove(id)>0){
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
		paperService.batchRemove(ids);
		return R.ok();
	}
	
}
