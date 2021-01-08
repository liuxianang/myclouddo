package com.bootdo.clouddoexam.service;

import com.bootdo.clouddoexam.domain.PaperDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-17 09:34:01
 */
public interface PaperService {
	
	PaperDO get(Integer id);
	
	List<PaperDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperDO paper);
	
	int update(PaperDO paper);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
