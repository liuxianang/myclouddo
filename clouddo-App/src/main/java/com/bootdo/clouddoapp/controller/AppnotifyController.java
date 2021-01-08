package com.bootdo.clouddoapp.controller;

import com.alibaba.fastjson.JSONObject;

import com.bootdo.clouddoapp.domain.AppnotifyDO;

import com.bootdo.clouddoapp.domain.AppshoppingcarDO;
import com.bootdo.clouddoapp.domain.AppsignDO;
import com.bootdo.clouddoapp.domain.imageDTO;
import com.bootdo.clouddoapp.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-11 13:34:17
 */

@Controller
@RequestMapping("/app")
public class AppnotifyController {
	@Autowired
	private AppnotifyService appnotifyService;
	@Autowired
	private AppuserService appuserService;
	@Autowired
	private AppsignService appsignService;
	@Autowired
	private AppshoppingcarService appshoppingcarService;
	@Autowired
	private imageService mageService;

	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		JSONObject jsonObject = new JSONObject();  //创建Json对象
		List<AppnotifyDO> aa = appnotifyService.listall();

		jsonObject.put("data", aa);//设置Json对象的属性
		return  jsonObject.toString();
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String name = request.getParameter("name");
		String password=request.getParameter("password");
		JSONObject jsonObject = new JSONObject();  //创建Json对象
		int result = appuserService.findbynameandpassword(name, password);
		if(result==1){
			jsonObject.put("msg", "true");    //设置Json对象的属性
		}else{
			jsonObject.put("msg", "false");
		}
		return jsonObject.toString();
	}
	@RequestMapping("/signrecord")
	@ResponseBody
	public String signrecord(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		JSONObject jsonObject = new JSONObject();  //创建Json对象
		List<AppsignDO> result = appsignService.findsignByphone(phone);

		jsonObject.put("data", result);    //设置Json对象的属性

		System.out.println("查询用户数"+result);

		return jsonObject.toString();
	}
	@RequestMapping("/dosign")
	@ResponseBody
	public String dosign(HttpServletRequest request) throws ParseException {
		String phone = request.getParameter("phone");
		String time = request.getParameter("time");
		String name = request.getParameter("name");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AppsignDO appsign = new AppsignDO();
		appsign.setPhone(phone);
		appsign.setDate(sdf.parse(time));
		appsign.setUsername(name);

		JSONObject jsonObject = new JSONObject();  //创建Json对象
		if(appsignService.save( appsign)>0){
			jsonObject.put("msg", true);    //设置Json对象的属性
		}else{
			jsonObject.put("msg", false);    //设置Json对象的属性
		}


		return jsonObject.toString();
	}
	@RequestMapping("/shoppingCarData")
	@ResponseBody
	public String shoppingCarData(HttpServletRequest request) throws ParseException {
		String phone = request.getParameter("phone");

		JSONObject jsonObject = new JSONObject();  //创建Json对象
		List<AppshoppingcarDO> store = appshoppingcarService.listfindbyphone(phone);
		List<Map> list= new ArrayList<Map>();
       for(int i=0;i<store.size();i++){
		   List<AppshoppingcarDO> result = appshoppingcarService.listfindbyphoneandstoreid(store.get(i).getUserphone(), store.get(i).getStoreid());
		   Map map = new HashMap();  //创建map对象
		   map.put("goods", result);
		   map.put("store_id", store.get(i).getStoreid());
		   map.put("store_name", store.get(i).getStorename());
		   list.add(map);
       }
		jsonObject.put("code", 200);
		jsonObject.put("datas",list);
		return jsonObject.toString();
	}
	@RequestMapping("/loadimage")
	@ResponseBody
	public String loadimage(HttpServletRequest request) throws ParseException {
		String phone = request.getParameter("phone");

		JSONObject jsonObject = new JSONObject();  //创建Json对象
		List<imageDTO> list = mageService.listall();

		jsonObject.put("code", 200);
		jsonObject.put("data",list);
		return jsonObject.toString();
	}

	}

