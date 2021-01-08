package com.bootdo.clouddoapp.dao;

import com.bootdo.clouddoapp.domain.AppnotifyDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-11 13:34:17
 */
@Mapper
public interface AppnotifyDao {

	AppnotifyDO get(Integer id);
	
	List<AppnotifyDO> list(Map<String, Object> map);
	List<AppnotifyDO> listall();
	int count(Map<String, Object> map);
	
	int save(AppnotifyDO appnotify);
	
	int update(AppnotifyDO appnotify);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
