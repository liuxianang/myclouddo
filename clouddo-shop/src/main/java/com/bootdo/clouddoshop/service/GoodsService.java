package com.bootdo.clouddoshop.service;

import com.bootdo.clouddoshop.domain.GoodsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:02:24
 */
public interface GoodsService {
	
	GoodsDO get(Integer id);
	GoodsDO getDetails(String productid);
	List<GoodsDO> list(Map<String, Object> map);
	List<GoodsDO> shopGoods(Map<String, Object> map);
	int count(Map<String, Object> map);
	
	int save(GoodsDO goods);
	
	int update(GoodsDO goods);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
