package com.bootdo.clouddoapp.service.impl;


import com.bootdo.clouddoapp.dao.AppsignDao;
import com.bootdo.clouddoapp.domain.AppsignDO;

import com.bootdo.clouddoapp.service.AppsignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AppsignServiceImpl implements AppsignService {
	@Autowired
	private AppsignDao appsignDao;
	
	@Override
	public AppsignDO get(Integer id){
		return appsignDao.get(id);
	}
	
	@Override
	public List<AppsignDO> list(Map<String, Object> map){
		return appsignDao.list(map);
	}

	@Override
	public List<AppsignDO> findsignByphone(String phone) {
		return  appsignDao.findsignByphone( phone);
	}

	@Override
	public int count(Map<String, Object> map){
		return appsignDao.count(map);
	}
	
	@Override
	public int save(AppsignDO appsign){
		return appsignDao.save(appsign);
	}
	
	@Override
	public int update(AppsignDO appsign){
		return appsignDao.update(appsign);
	}
	
	@Override
	public int remove(Integer id){
		return appsignDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return appsignDao.batchRemove(ids);
	}
	
}
