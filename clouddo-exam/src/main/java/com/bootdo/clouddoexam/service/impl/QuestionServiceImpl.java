package com.bootdo.clouddoexam.service.impl;

import com.bootdo.clouddoexam.domain.treeQus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoexam.dao.QuestionDao;
import com.bootdo.clouddoexam.domain.QuestionDO;
import com.bootdo.clouddoexam.service.QuestionService;



@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public QuestionDO get(Integer id){
		return questionDao.get(id);
	}
	
	@Override
	public List<QuestionDO> list(Map<String, Object> map){
		return questionDao.list(map);
	}
	@Override
	public List<treeQus> findtree(){
		return questionDao.findtree();
	}
	@Override
	public int count(Map<String, Object> map){
		return questionDao.count(map);
	}
	
	@Override
	public int save(QuestionDO question){
		return questionDao.save(question);
	}
	
	@Override
	public int update(QuestionDO question){
		return questionDao.update(question);
	}
	
	@Override
	public int remove(Integer id){
		return questionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return questionDao.batchRemove(ids);
	}

	@Override
	public List<QuestionDO> findlist(Integer[] ids) {
		return questionDao.findlist(ids);
	}

	@Override
	public List<QuestionDO> findall() {
		return questionDao.findall();
	}

}
