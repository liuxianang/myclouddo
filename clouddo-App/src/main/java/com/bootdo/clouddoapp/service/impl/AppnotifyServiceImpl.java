package com.bootdo.clouddoapp.service.impl;


import com.bootdo.clouddoapp.dao.AppnotifyDao;

import com.bootdo.clouddoapp.domain.AppnotifyDO;
import com.bootdo.clouddoapp.service.AppnotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AppnotifyServiceImpl implements AppnotifyService {
	@Autowired
	private AppnotifyDao appnotifyDao;
	
	@Override
	public AppnotifyDO get(Integer id){
		return appnotifyDao.get(id);
	}
	
	@Override
	public List<AppnotifyDO> list(Map<String, Object> map){
		return appnotifyDao.list(map);
	}

	@Override
	public List<AppnotifyDO> listall() {
		return appnotifyDao.listall();
	}

	@Override
	public int count(Map<String, Object> map){
		return appnotifyDao.count(map);
	}
	
	@Override
	public int save(AppnotifyDO appnotify){
		return appnotifyDao.save(appnotify);
	}
	
	@Override
	public int update(AppnotifyDO appnotify){
		return appnotifyDao.update(appnotify);
	}
	
	@Override
	public int remove(Integer id){
		return appnotifyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return appnotifyDao.batchRemove(ids);
	}



}
