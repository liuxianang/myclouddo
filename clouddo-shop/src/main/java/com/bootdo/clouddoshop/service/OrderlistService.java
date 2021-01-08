package com.bootdo.clouddoshop.service;

import com.bootdo.clouddoshop.domain.OrderlistDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:05:42
 */
public interface OrderlistService {
	
	OrderlistDO get(String orderid);
	
	List<OrderlistDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderlistDO orderlist);
	
	int update(OrderlistDO orderlist);
	
	int remove(String orderid);
	
	int batchRemove(String[] orderids);
}
