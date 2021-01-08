package com.bootdo.clouddoshop.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bootdo.clouddoshop.domain.AddresslistDO;
import com.bootdo.clouddoshop.domain.CartlistDO;
import com.bootdo.clouddoshop.domain.UserDO;
import com.bootdo.clouddoshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoshop.domain.OrderlistDO;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:05:42
 */

@RestController
@RequestMapping("/orderlist")
public class OrderlistController {
	@Autowired
	private OrderlistService orderlistService;
	@Autowired
	private AddresslistService addresslistService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartlistService cartlistService;
	@Autowired
	PayService payService;
	@GetMapping
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderlistDO> orderlistList = orderlistService.list(query);
		int total = orderlistService.count(query);
		PageUtils pageUtils = new PageUtils(orderlistList, total);
		return R.ok().put("page",pageUtils);
	}
	@GetMapping("/ordershoplist")
	public  R ordershoplist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<OrderlistDO> orderlistList = orderlistService.list(query);
		return R.ok().put("data",orderlistList);
	}
	@GetMapping("/add")
	String add(){
	    return "clouddoexam/orderlist/add";
	}

	@GetMapping("/payOrder")
	String payOrder(@RequestParam Map<String, Object> params) throws IOException {
		AddresslistDO addresslist = addresslistService.get(Integer.parseInt(params.get("addressId").toString()));
		UserDO user = userService.getbyuserid(params.get("userid").toString());
		System.out.println("用户及地址"+user+">>>>>>>>>"+addresslist);
		System.out.println("支付方式"+params.get("selectType").toString());
		if(user!=null&&addresslist!=null){
			HashMap<String, Object> newparams = new HashMap<String, Object>();
			newparams.put("userid",params.get("userid").toString());
			List<CartlistDO> cartlist = cartlistService.list(newparams);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat ordersdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			OrderlistDO orderlist=new OrderlistDO();
			for(int i=0;i<cartlist.size();i++){
				String orderId = params.get("selectType").toString()+""+ordersdf.format(date)+"_"+i;
				orderlist.setUserid(cartlist.get(i).getUserid());
				orderlist.setOrderid(orderId);
				orderlist.setProductid(cartlist.get(i).getProductid());
				orderlist.setProductname(cartlist.get(i).getProductname());
				orderlist.setProductprice(cartlist.get(i).getProductprice());
				orderlist.setProductnum(cartlist.get(i).getProductnum());
				orderlist.setProductimg(cartlist.get(i).getProductimg());
				orderlist.setTotalprice(cartlist.get(i).getTotalprice());
				orderlist.setStreetname(addresslist.getStreetname());
				orderlist.setPostname(addresslist.getUsername());
				orderlist.setPostcode(addresslist.getPostcode()+"");
				orderlist.setTel(addresslist.getTel());
				orderlist.setItemprice(cartlist.get(i).getTotalprice());
				orderlist.setDiscount(params.get("discount").toString());
				orderlist.setShipprice(params.get("shipPrice").toString());
				orderlist.setFreightrisk(params.get("freightRisk").toString());
				orderlist.setCreatedate(sdf.format(date));
				orderlist.setIfpay(0);
				orderlistService.save(orderlist);
			}
			cartlistService.removebyuserid(params.get("userid").toString());
		/*	System.out.println("调用支付微服务加载二维码>>>>>>>"+payService.LoadPayQRCode());*/
			return "{\"status\":\"1\",\"msg\":\"suc\",\"result\":\"6225202005141128109\"}";
		}else{
			return "{\"status\":\"-1\",\"msg\":\"数据异常\"}";
		}

	}
	@GetMapping("/updateOrder")
	String updateOrder(){

		return "{\"status\":\"1\",\"msg\":\"\",\"result\":\"\"}";
	}
	@GetMapping("/delOrder")
	String delOrder(String orderid){
		if(orderlistService.remove(orderid)>0){
			return "{\"status\":\"1\",\"msg\":\"操作成功\",\"result\":\"\"}";
		}else{
			return "{\"status\":\"-1\",\"msg\":\"删除失败\",\"result\":\"\"}";
		}
	}
	@GetMapping("/edit/{orderid}")
	String edit(@PathVariable("orderid") String orderid,Model model){
		OrderlistDO orderlist = orderlistService.get(orderid);
		model.addAttribute("orderlist", orderlist);
	    return "clouddoexam/orderlist/edit";
	}
	
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  OrderlistDO orderlist){
		if(orderlistService.save(orderlist)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  OrderlistDO orderlist){
		orderlistService.update(orderlist);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( String orderid){
		if(orderlistService.remove(orderid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] orderids){
		orderlistService.batchRemove(orderids);
		return R.ok();
	}

}
