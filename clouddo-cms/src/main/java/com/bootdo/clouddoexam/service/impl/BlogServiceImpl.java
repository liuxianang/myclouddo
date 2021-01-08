package com.bootdo.clouddoexam.service.impl;

import com.bootdo.clouddoexam.dao.BlogDao;

import com.bootdo.clouddoexam.domain.BlogDO;

import com.bootdo.clouddoexam.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao blogMapper;

	@Override
	public BlogDO get(Long cid){
		return blogMapper.get(cid);
	}

	@Override
	public List<BlogDO> list(Map<String, Object> map){
		return blogMapper.list(map);
	}

	@Override
	public List<BlogDO> listall() {

		return blogMapper.listall();
	}
	@Override
	public List<BlogDO> listallweiguan() {

		return blogMapper.listallweiguan();
	}
	@Override
	public int count(Map<String, Object> map){
		return blogMapper.count(map);
	}

	@Override
	public int save(BlogDO bContent){
		return blogMapper.save(bContent);
	}

	@Override
	public int update(BlogDO bContent){
		return blogMapper.update(bContent);
	}

	@Override
	public int remove(Long cid){
		return blogMapper.remove(cid);
	}

	@Override
	public int batchRemove(Long[] cids){
		return blogMapper.batchRemove(cids);
	}
}
