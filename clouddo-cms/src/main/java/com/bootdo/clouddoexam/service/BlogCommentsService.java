package com.bootdo.clouddoexam.service;

import com.bootdo.clouddoexam.domain.BlogCommentsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-09 14:50:19
 */
public interface BlogCommentsService {
	
	BlogCommentsDO get(Integer id);
	
	List<BlogCommentsDO> list(Map<String, Object> map);
	List<BlogCommentsDO> listbyblogid(long id);
	int count(Map<String, Object> map);
	
	int save(BlogCommentsDO blogComments);
	int saveComments(BlogCommentsDO blogComments);
	int update(BlogCommentsDO blogComments);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
