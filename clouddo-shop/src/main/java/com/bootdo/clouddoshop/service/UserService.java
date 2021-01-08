package com.bootdo.clouddoshop.service;

import com.bootdo.clouddoshop.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:10:13
 */
public interface UserService {
	
	UserDO get(Integer id);
	UserDO getbyuserid(String userid);
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
