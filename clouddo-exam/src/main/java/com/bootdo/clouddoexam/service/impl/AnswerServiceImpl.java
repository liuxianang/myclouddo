package com.bootdo.clouddoexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoexam.dao.AnswerDao;
import com.bootdo.clouddoexam.domain.AnswerDO;
import com.bootdo.clouddoexam.service.AnswerService;



@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	public AnswerDO get(Integer id){
		return answerDao.get(id);
	}
	
	@Override
	public List<AnswerDO> list(Map<String, Object> map){
		return answerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return answerDao.count(map);
	}
	
	@Override
	public int save(AnswerDO answer){
		return answerDao.save(answer);
	}
	
	@Override
	public int update(AnswerDO answer){
		return answerDao.update(answer);
	}
	
	@Override
	public int remove(Integer id){
		return answerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return answerDao.batchRemove(ids);
	}
	
}
