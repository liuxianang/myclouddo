package com.bootdo.clouddoexam.service;

import com.bootdo.clouddoexam.domain.BlogDO;


import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-11 20:04:05
 */
public interface BlogService {

	BlogDO get(Long cid);

	List<BlogDO> list(Map<String, Object> map);
	List<BlogDO> listall();
	List<BlogDO> listallweiguan();//博客围观次数
	int count(Map<String, Object> map);

	int save(BlogDO bContent);

	int update(BlogDO bContent);

	int remove(Long cid);

	int batchRemove(Long[] cids);
}
