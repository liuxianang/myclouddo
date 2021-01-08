package com.bootdo.clouddoBase.service;



import com.bootdo.clouddoBase.domain.SeimicrawlerDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-12-09 17:20:56
 */
public interface SeimicrawlerService {
	
	SeimicrawlerDO get(Integer id);
	
	List<SeimicrawlerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeimicrawlerDO seimicrawler);
	
	int update(SeimicrawlerDO seimicrawler);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
