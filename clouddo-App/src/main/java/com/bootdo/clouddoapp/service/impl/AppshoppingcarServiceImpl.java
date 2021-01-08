package com.bootdo.clouddoapp.service.impl;


import com.bootdo.clouddoapp.dao.AppshoppingcarDao;
import com.bootdo.clouddoapp.domain.AppshoppingcarDO;

import com.bootdo.clouddoapp.service.AppshoppingcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AppshoppingcarServiceImpl implements AppshoppingcarService {
	@Autowired
	private AppshoppingcarDao appshoppingcarDao;
	
	@Override
	public AppshoppingcarDO get(String id){
		return appshoppingcarDao.get(id);
	}
	
	@Override
	public List<AppshoppingcarDO> list(Map<String, Object> map){
		return appshoppingcarDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return appshoppingcarDao.count(map);
	}
	
	@Override
	public int save(AppshoppingcarDO appshoppingcar){
		return appshoppingcarDao.save(appshoppingcar);
	}
	
	@Override
	public int update(AppshoppingcarDO appshoppingcar){
		return appshoppingcarDao.update(appshoppingcar);
	}
	
	@Override
	public int remove(String id){
		return appshoppingcarDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return appshoppingcarDao.batchRemove(ids);
	}

	@Override
	public List<AppshoppingcarDO> listfindbyphone(String phone) {
		return appshoppingcarDao.listfindbyphone(phone);
	}

	@Override
	public List<AppshoppingcarDO> listfindbyphoneandstoreid(String phone, String storeid) {
		return appshoppingcarDao.listfindbyphoneandstoreid(phone,storeid);
	}


}
