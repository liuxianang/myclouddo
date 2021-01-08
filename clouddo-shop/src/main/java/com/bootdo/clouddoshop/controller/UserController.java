package com.bootdo.clouddoshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoshop.domain.UserDO;
import com.bootdo.clouddoshop.service.UserService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:10:13
 */

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return R.ok().put("page",pageUtils);
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/user/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		UserDO user = userService.get(id);
		model.addAttribute("user", user);
	    return "clouddoexam/user/edit";
	}
	@GetMapping("/checkLogin")
	String checkLogin(@RequestParam Map<String, Object> params){
    if (params.get("name").toString().equals("")){
		Map<String, Object> map=new HashMap<>();
		map.put("status","10001");
		map.put("msg","当前未登录");
		map.put("result","");
		return   JSONObject.toJSONString(map);
	} else{
		Map<String, Object> map=new HashMap<>();
		map.put("status","1");
		map.put("msg","已登录");
		System.out.println(">>>>>"+params.get("id")+">>>>>>");
		List<UserDO> userList=new ArrayList<UserDO>();
		UserDO userdo=userService.get(Integer.parseInt(params.get("id").toString()));
		userList.add(userdo);
		map.put("result",userList);
		return   JSONObject.toJSONString(map);
    }
	}
	@GetMapping("/loginshop")
	String Loginshop(@RequestParam Map<String, Object> params){
		Map<String, Object> newparams = new HashMap<String, Object>();
		newparams.put("userid",params.get("userId"));
		Query query = new Query(newparams);
		List<UserDO> userList =userService.list(query);
		if(userList.size()>0){
            if(userList.get(0).getUserpwd().equals(params.get("userPw"))){
				return "{\"status\":\"1\",\"msg\":\"登录成功!\",\"result\":[{\"id\":1,\"userId\":\"123456\",\"userName\":\"冷色调16\",\"userPwd\":\"qwer1234\",\"myPhoto\":\"user-avatar\"}]}";
			}else{
				return "{\"status\":\"-2\",\"msg\":\"password error\"}";
			}
		}else{
			return "{\"status\":\"404\",\"msg\":\"user not exist\"}";
		}
	}
	@GetMapping("/loginoutshop")
	String loginoutshop(@RequestParam Map<String, Object> params){
		return"{\"status\":\"1\",\"msg\":\"\",\"result\":\"\"}";
	}
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  UserDO user){
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  UserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(userService.remove(id)>0){
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
		userService.batchRemove(ids);
		return R.ok();
	}
	
}
