package com.bootdo.clouddoshop.controller;

import java.util.List;
import java.util.Map;


import com.bootdo.clouddoshop.domain.AddresslistDO;
import com.bootdo.clouddoshop.service.AddresslistService;
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
 * @date 2020-05-10 20:18:04
 */

@RestController
@RequestMapping("/addresslist")
public class AddresslistController {
	@Autowired
	private AddresslistService addresslistService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AddresslistDO> addresslistList = addresslistService.list(query);
		int total = addresslistService.count(query);
		PageUtils pageUtils = new PageUtils(addresslistList, total);
		return R.ok().put("page",pageUtils);
	}
	@GetMapping("/addressshopList")
	public R addressshopList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<AddresslistDO> addresslistList = addresslistService.list(query);
		return R.ok().put("data",addresslistList);
	}
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/addresslist/add";
	}

	@GetMapping("/edit/{addressid}")

	String edit(@PathVariable("addressid") Integer addressid,Model model){
		AddresslistDO addresslist = addresslistService.get(addressid);
		model.addAttribute("addresslist", addresslist);
	    return "clouddoexam/addresslist/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  AddresslistDO addresslist){
		if(addresslistService.save(addresslist)>0){
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/saveshopaddress")
	public R saveshopaddress(@RequestParam Map<String, Object> params){
		AddresslistDO addresslist = new AddresslistDO();
		addresslist.setTel(params.get("tel").toString());
		addresslist.setPostcode(Integer.parseInt(params.get("postCode").toString()));
		addresslist.setStreetname(params.get("streetName").toString());
		addresslist.setUsername(params.get("userName").toString());
		addresslist.setUserid(params.get("userId").toString());
		addresslist.setIsdefault(Integer.parseInt(params.get("isDefault").toString()));
		System.out.println(">>>>>>>>>>>>>>aaaa"+addresslist);
		if(addresslistService.save(addresslist)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  AddresslistDO addresslist){
		addresslistService.update(addresslist);
		return R.ok();
	}
	/**
	 * 删除
	 */
	@GetMapping("deleteAdr")
	public R deleteAdr( Integer addressid){
		if(addresslistService.remove(addressid)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer addressid){
		if(addresslistService.remove(addressid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] addressids){
		addresslistService.batchRemove(addressids);
		return R.ok();
	}
	
}
