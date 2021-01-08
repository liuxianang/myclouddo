package com.bootdo.clouddoadmin.dao;



import java.util.List;
import java.util.Map;

import com.bootdo.clouddoadmin.domain.NoticeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-29 17:22:49
 */
@Mapper
public interface NoticeDao {

	NoticeDO get(Integer id);
	
	List<NoticeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NoticeDO notice);
	
	int update(NoticeDO notice);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
