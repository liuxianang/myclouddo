package com.bootdo.clouddoshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoshop.dao.GoodsDao;
import com.bootdo.clouddoshop.domain.GoodsDO;
import com.bootdo.clouddoshop.service.GoodsService;



@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public GoodsDO get(Integer id){
		return goodsDao.get(id);
	}
	@Override
	public GoodsDO getDetails(String  productid){
		return goodsDao.getDetails(productid);
	}
	@Override
	public List<GoodsDO> list(Map<String, Object> map){
		return goodsDao.list(map);
	}
	@Override
	public List<GoodsDO> shopGoods(Map<String, Object> map){
		return goodsDao.shopGoods(map);
	}
	@Override
	public int count(Map<String, Object> map){
		return goodsDao.count(map);
	}
	
	@Override
	public int save(GoodsDO goods){
		return goodsDao.save(goods);
	}
	
	@Override
	public int update(GoodsDO goods){
		return goodsDao.update(goods);
	}
	
	@Override
	public int remove(Integer id){
		return goodsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return goodsDao.batchRemove(ids);
	}
	
}
