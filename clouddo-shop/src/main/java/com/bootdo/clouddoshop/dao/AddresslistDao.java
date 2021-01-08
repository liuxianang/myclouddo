package com.bootdo.clouddoshop.dao;



import java.util.List;
import java.util.Map;

import com.bootdo.clouddoshop.domain.AddresslistDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 20:18:04
 */
@Mapper
public interface AddresslistDao {

	AddresslistDO get(Integer addressid);
	
	List<AddresslistDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AddresslistDO addresslist);
	
	int update(AddresslistDO addresslist);
	
	int remove(Integer addressId);
	
	int batchRemove(Integer[] addressids);
}
