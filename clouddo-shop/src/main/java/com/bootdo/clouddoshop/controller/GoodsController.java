package com.bootdo.clouddoshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoshop.domain.GoodsDO;
import com.bootdo.clouddoshop.service.GoodsService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:02:24
 */

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GoodsDO> goodsList = goodsService.list(query);
		int total = goodsService.count(query);
		PageUtils pageUtils = new PageUtils(goodsList, total);
		return R.ok().put("page",pageUtils);
	}
	@GetMapping("listgoods")
	public R listgoods(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<GoodsDO> goodsList = goodsService.shopGoods(query);
		int total = goodsService.count(query);
		PageUtils pageUtils = new PageUtils(goodsList, total);
		return R.ok().put("page",pageUtils);
	}
	@GetMapping("getDetails")
	public R getDetails(String  productid){
		List<GoodsDO> goodsList = new ArrayList<GoodsDO>();
		goodsList.add(goodsService.getDetails(productid));
		return R.ok().put("data",goodsList);
	}
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/goods/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		GoodsDO goods = goodsService.get(id);
		model.addAttribute("goods", goods);
	    return "clouddoexam/goods/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  GoodsDO goods){
		if(goodsService.save(goods)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  GoodsDO goods){
		goodsService.update(goods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(goodsService.remove(id)>0){
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
		goodsService.batchRemove(ids);
		return R.ok();
	}
	
}
