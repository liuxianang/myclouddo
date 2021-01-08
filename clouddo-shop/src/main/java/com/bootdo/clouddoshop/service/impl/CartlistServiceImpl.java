package com.bootdo.clouddoshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoshop.dao.CartlistDao;
import com.bootdo.clouddoshop.domain.CartlistDO;
import com.bootdo.clouddoshop.service.CartlistService;



@Service
public class CartlistServiceImpl implements CartlistService {
	@Autowired
	private CartlistDao cartlistDao;
	
	@Override
	public CartlistDO get(Integer cartid){
		return cartlistDao.get(cartid);
	}
	
	@Override
	public List<CartlistDO> list(Map<String, Object> map){
		return cartlistDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return cartlistDao.count(map);
	}
	
	@Override
	public int save(CartlistDO cartlist){
		return cartlistDao.save(cartlist);
	}
	
	@Override
	public int update(CartlistDO cartlist){
		return cartlistDao.update(cartlist);
	}
	
	@Override
	public int remove(Integer cartid){
		return cartlistDao.remove(cartid);
	}
	@Override
	public int removebyuserid(String userid){
		return cartlistDao.removebyuserid(userid);
	}
	@Override
	public int batchRemove(Integer[] cartids){
		return cartlistDao.batchRemove(cartids);
	}
	
}
