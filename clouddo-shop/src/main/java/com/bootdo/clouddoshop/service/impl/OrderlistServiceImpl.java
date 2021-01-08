package com.bootdo.clouddoshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoshop.dao.OrderlistDao;
import com.bootdo.clouddoshop.domain.OrderlistDO;
import com.bootdo.clouddoshop.service.OrderlistService;



@Service
public class OrderlistServiceImpl implements OrderlistService {
	@Autowired
	private OrderlistDao orderlistDao;
	
	@Override
	public OrderlistDO get(String orderid){
		return orderlistDao.get(orderid);
	}
	
	@Override
	public List<OrderlistDO> list(Map<String, Object> map){
		return orderlistDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderlistDao.count(map);
	}
	
	@Override
	public int save(OrderlistDO orderlist){
		return orderlistDao.save(orderlist);
	}
	
	@Override
	public int update(OrderlistDO orderlist){
		return orderlistDao.update(orderlist);
	}
	
	@Override
	public int remove(String orderid){
		return orderlistDao.remove(orderid);
	}
	
	@Override
	public int batchRemove(String[] orderids){
		return orderlistDao.batchRemove(orderids);
	}
	
}
