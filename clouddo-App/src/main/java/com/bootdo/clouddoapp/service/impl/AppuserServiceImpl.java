package com.bootdo.clouddoapp.service.impl;


import com.bootdo.clouddoapp.dao.AppuserDao;

import com.bootdo.clouddoapp.domain.AppuserDO;
import com.bootdo.clouddoapp.service.AppuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AppuserServiceImpl implements AppuserService {
	@Autowired
	private AppuserDao appuserDao;
	
	@Override
	public AppuserDO get(Integer id){
		return appuserDao.get(id);
	}
	
	@Override
	public List<AppuserDO> list(Map<String, Object> map){
		return appuserDao.list(map);
	}

	@Override
	public int findbynameandpassword(String name, String password) {
		return appuserDao.findbynameandpassword(name,password);
	}

	@Override
	public int count(Map<String, Object> map){
		return appuserDao.count(map);
	}
	
	@Override
	public int save(AppuserDO appuser){
		return appuserDao.save(appuser);
	}
	
	@Override
	public int update(AppuserDO appuser){
		return appuserDao.update(appuser);
	}
	
	@Override
	public int remove(Integer id){
		return appuserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return appuserDao.batchRemove(ids);
	}
	
}
