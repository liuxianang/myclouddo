package com.bootdo.clouddoexam.dao;

import com.bootdo.clouddoexam.domain.TestDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-14 13:56:02
 */
@Mapper
public interface TestDao {

	TestDO get(Integer id);
	
	List<TestDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestDO test);
	
	int update(TestDO test);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
