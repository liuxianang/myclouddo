package com.bootdo.clouddoexam.service;

import com.bootdo.clouddoexam.domain.AnswerDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-14 14:12:54
 */
public interface AnswerService {
	
	AnswerDO get(Integer id);
	
	List<AnswerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AnswerDO answer);
	
	int update(AnswerDO answer);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
