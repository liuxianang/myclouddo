package com.bootdo.clouddoBase.dao;



import java.util.List;
import java.util.Map;

import com.bootdo.clouddoBase.domain.SeimicrawlerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-12-09 17:20:56
 */
@Mapper
public interface SeimicrawlerDao {

	SeimicrawlerDO get(Integer id);
	
	List<SeimicrawlerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeimicrawlerDO seimicrawler);
	
	int update(SeimicrawlerDO seimicrawler);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
