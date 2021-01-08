package com.bootdo.clouddoshop.dao;

import com.bootdo.clouddoshop.domain.OrderlistDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:05:42
 */
@Mapper
public interface OrderlistDao {

	OrderlistDO get(String orderid);
	
	List<OrderlistDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderlistDO orderlist);
	
	int update(OrderlistDO orderlist);
	
	int remove(String orderId);
	
	int batchRemove(String[] orderids);
}
