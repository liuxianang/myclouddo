package com.bootdo.clouddoapp.dao;


import com.bootdo.clouddoapp.domain.AppshoppingcarDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-10 16:00:56
 */
@Mapper
public interface AppshoppingcarDao {

	AppshoppingcarDO get(String id);
	
	List<AppshoppingcarDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AppshoppingcarDO appshoppingcar);
	
	int update(AppshoppingcarDO appshoppingcar);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	List<AppshoppingcarDO> listfindbyphone(String phone);//分组查询
	List<AppshoppingcarDO> listfindbyphoneandstoreid(String phone, String storeid);//分组查询
}
