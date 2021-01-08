package com.bootdo.clouddoexam.dao;

import com.bootdo.clouddoexam.domain.PaperDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-17 09:34:01
 */
@Mapper
public interface PaperDao {

	PaperDO get(Integer id);
	
	List<PaperDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperDO paper);
	
	int update(PaperDO paper);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
