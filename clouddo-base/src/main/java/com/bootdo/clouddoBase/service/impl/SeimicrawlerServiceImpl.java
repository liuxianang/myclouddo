package com.bootdo.clouddoBase.service.impl;

import com.bootdo.clouddoBase.dao.SeimicrawlerDao;
import com.bootdo.clouddoBase.domain.SeimicrawlerDO;
import com.bootdo.clouddoBase.service.SeimicrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service
public class SeimicrawlerServiceImpl implements SeimicrawlerService {
	@Autowired
	private SeimicrawlerDao seimicrawlerDao;
	
	@Override
	public SeimicrawlerDO get(Integer id){
		return seimicrawlerDao.get(id);
	}
	
	@Override
	public List<SeimicrawlerDO> list(Map<String, Object> map){
		return seimicrawlerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seimicrawlerDao.count(map);
	}
	
	@Override
	public int save(SeimicrawlerDO seimicrawler){
		return seimicrawlerDao.save(seimicrawler);
	}
	
	@Override
	public int update(SeimicrawlerDO seimicrawler){
		return seimicrawlerDao.update(seimicrawler);
	}
	
	@Override
	public int remove(Integer id){
		return seimicrawlerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return seimicrawlerDao.batchRemove(ids);
	}
	
}
