package com.bootdo.clouddoshop.service;



import com.bootdo.clouddoshop.domain.AddresslistDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 20:18:04
 */
public interface AddresslistService {
	
	AddresslistDO get(Integer addressid);
	
	List<AddresslistDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AddresslistDO addresslist);
	
	int update(AddresslistDO addresslist);
	
	int remove(Integer addressid);
	
	int batchRemove(Integer[] addressids);
}
