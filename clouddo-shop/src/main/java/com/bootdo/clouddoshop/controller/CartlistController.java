package com.bootdo.clouddoshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.bootdo.clouddoshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoshop.domain.CartlistDO;
import com.bootdo.clouddoshop.service.CartlistService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 20:58:19
 */

@RestController
@RequestMapping("/cartlist")
public class CartlistController {
	@Autowired
	private CartlistService cartlistService;
	@Autowired
	private GoodsService goodsService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CartlistDO> cartlistList = cartlistService.list(query);
		int total = cartlistService.count(query);
		PageUtils pageUtils = new PageUtils(cartlistList, total);
		return R.ok().put("page",pageUtils);
	}
	@GetMapping("/cartshoplist")
	public R cartshoplist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CartlistDO> cartlistList = cartlistService.list(query);
		return R.ok().put("data",cartlistList);
	}

	@GetMapping("/add")

	String add(){
	    return "clouddoexam/cartlist/add";
	}
	@GetMapping("/editSelectAll")
	String editSelectAll()
	{
		return "{\"status\":\"1\",\"msg\":\"更新成功\",\"result\":{\"fieldCount\":0,\"affectedRows\":1,\"insertId\":0,\"serverStatus\":34,\"warningCount\":0,\"message\":\"(Rows matched: 1  Changed: 1  Warnings: 0\",\"protocol41\":true,\"changedRows\":1}}";
	}
	@GetMapping("/edit/{cartid}")
	String edit(@PathVariable("cartid") Integer cartid,Model model){
		CartlistDO cartlist = cartlistService.get(cartid);
		model.addAttribute("cartlist", cartlist);
	    return "clouddoexam/cartlist/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  CartlistDO cartlist){
		if(cartlistService.save(cartlist)>0){
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/addcart")
	public String addcart(@RequestParam Map<String, Object> params){
		System.out.println(goodsService.getDetails(params.get("productId").toString()));
		if(goodsService.getDetails(params.get("productId").toString())!=null){
			CartlistDO cartlist = new CartlistDO();
			cartlist.setChecked(params.get("checked").toString());
			cartlist.setProductid(Integer.parseInt(params.get("productId").toString()));
			cartlist.setProductnum(Integer.parseInt(params.get("productNum").toString()));
			cartlist.setProductprice(Integer.parseInt(params.get("productPrice").toString()));
			cartlist.setProductname(params.get("productName").toString());
			cartlist.setProductimg(params.get("productImg").toString());
			cartlist.setUserid(params.get("userid").toString());
			cartlist.setTotalprice(""+(Integer.parseInt(params.get("productNum").toString())*Integer.parseInt(params.get("productPrice").toString())));
			cartlistService.save(cartlist);
			Map<String, Object> map=new HashMap<>();
			map.put("status","1");
			map.put("msg","添加购物车成功");
			map.put("result","");
			map.put("cartcount",cartlistService.count(params));
			return   JSONObject.toJSONString(map);
		}else{
			Map<String, Object> map=new HashMap<>();
			map.put("status","10001");
			map.put("msg","商品已售完");
			map.put("result","");
			return   JSONObject.toJSONString(map);
		}

	}
	@GetMapping("/delcart")
	public R delcart( Integer cartid){
		if(cartlistService.remove(cartid)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  CartlistDO cartlist){
		cartlistService.update(cartlist);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer cartid){
		if(cartlistService.remove(cartid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] cartids){
		cartlistService.batchRemove(cartids);
		return R.ok();
	}
	
}
