package com.bootdo.clouddoexam.service;

import com.bootdo.clouddoexam.domain.QuestionDO;
import com.bootdo.clouddoexam.domain.treeQus;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-16 16:07:43
 */
public interface QuestionService {
	
	QuestionDO get(Integer id);
	
	List<QuestionDO> list(Map<String, Object> map);
	List<treeQus> findtree();
	int count(Map<String, Object> map);
	
	int save(QuestionDO question);
	
	int update(QuestionDO question);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	List<QuestionDO> findlist(Integer[] ids);
	List<QuestionDO> findall();
}
