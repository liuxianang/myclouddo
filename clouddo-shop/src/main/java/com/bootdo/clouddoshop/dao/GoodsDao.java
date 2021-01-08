package com.bootdo.clouddoshop.dao;

import com.bootdo.clouddoshop.domain.GoodsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:02:24
 */
@Mapper
public interface GoodsDao {

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
