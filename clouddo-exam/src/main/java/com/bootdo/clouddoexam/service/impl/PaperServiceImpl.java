package com.bootdo.clouddoexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.clouddoexam.dao.PaperDao;
import com.bootdo.clouddoexam.domain.PaperDO;
import com.bootdo.clouddoexam.service.PaperService;



@Service
public class PaperServiceImpl implements PaperService {
	@Autowired
	private PaperDao paperDao;
	
	@Override
	public PaperDO get(Integer id){
		return paperDao.get(id);
	}
	
	@Override
	public List<PaperDO> list(Map<String, Object> map){
		return paperDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paperDao.count(map);
	}
	
	@Override
	public int save(PaperDO paper){
		return paperDao.save(paper);
	}
	
	@Override
	public int update(PaperDO paper){
		return paperDao.update(paper);
	}
	
	@Override
	public int remove(Integer id){
		return paperDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return paperDao.batchRemove(ids);
	}
	
}
