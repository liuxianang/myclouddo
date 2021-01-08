package com.bootdo.clouddoapp.dao;



import com.bootdo.clouddoapp.domain.AppuserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-21 14:05:07
 */
@Mapper
public interface AppuserDao {

	AppuserDO get(Integer id);
	
	List<AppuserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int findbynameandpassword(String name, String password);

	int save(AppuserDO appuser);
	
	int update(AppuserDO appuser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
