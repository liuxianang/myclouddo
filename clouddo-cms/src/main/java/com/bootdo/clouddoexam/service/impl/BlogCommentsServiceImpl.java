package com.bootdo.clouddoexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoexam.dao.BlogCommentsDao;
import com.bootdo.clouddoexam.domain.BlogCommentsDO;
import com.bootdo.clouddoexam.service.BlogCommentsService;



@Service
public class BlogCommentsServiceImpl implements BlogCommentsService {
	@Autowired
	private BlogCommentsDao blogCommentsDao;
	
	@Override
	public BlogCommentsDO get(Integer id){
		return blogCommentsDao.get(id);
	}
	
	@Override
	public List<BlogCommentsDO> list(Map<String, Object> map){
		return blogCommentsDao.list(map);
	}
	@Override
	public List<BlogCommentsDO> listbyblogid(long id){
		return blogCommentsDao.listbyblogid(id);
	}
	@Override
	public int count(Map<String, Object> map){
		return blogCommentsDao.count(map);
	}
	
	@Override
	public int save(BlogCommentsDO blogComments){
		return blogCommentsDao.save(blogComments);
	}
	@Override
	public int saveComments(BlogCommentsDO blogComments){
		return blogCommentsDao.saveComments(blogComments);
	}
	
	@Override
	public int update(BlogCommentsDO blogComments){
		return blogCommentsDao.update(blogComments);
	}
	
	@Override
	public int remove(Integer id){
		return blogCommentsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return blogCommentsDao.batchRemove(ids);
	}
	
}
