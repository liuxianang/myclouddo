package com.bootdo.clouddoshop.dao;

import com.bootdo.clouddoshop.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:10:13
 */
@Mapper
public interface UserDao {

	UserDO get(Integer id);
	UserDO getbyuserid(String userid);
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
