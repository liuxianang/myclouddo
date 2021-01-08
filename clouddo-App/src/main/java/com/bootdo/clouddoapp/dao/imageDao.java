package com.bootdo.clouddoapp.dao;


import com.bootdo.clouddoapp.domain.imageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-11 20:04:05
 */
@Mapper
public interface imageDao {

	imageDTO get(Long id);
	
	List<imageDTO> list(Map<String, Object> map);
	List<imageDTO> listall();
	int count(Map<String, Object> map);
	
	int save(imageDTO file);
	
	int update(imageDTO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
