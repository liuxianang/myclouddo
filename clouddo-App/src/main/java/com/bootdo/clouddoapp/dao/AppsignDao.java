package com.bootdo.clouddoapp.dao;


import com.bootdo.clouddoapp.domain.AppsignDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-27 13:00:30
 */
@Mapper
public interface AppsignDao {

	AppsignDO get(Integer id);
	
	List<AppsignDO> list(Map<String, Object> map);
	List<AppsignDO> findsignByphone(String phone);
	int count(Map<String, Object> map);
	
	int save(AppsignDO appsign);
	
	int update(AppsignDO appsign);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
