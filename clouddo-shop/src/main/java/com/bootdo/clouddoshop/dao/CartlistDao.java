package com.bootdo.clouddoshop.dao;

import com.bootdo.clouddoshop.domain.CartlistDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 20:58:19
 */
@Mapper
public interface CartlistDao {

	CartlistDO get(Integer cartid);
	
	List<CartlistDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CartlistDO cartlist);
	
	int update(CartlistDO cartlist);
	
	int remove(Integer cartId);
	int removebyuserid(String  userid);
	int batchRemove(Integer[] cartids);
}
