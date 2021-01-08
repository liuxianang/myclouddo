package com.bootdo.clouddoshop.service.impl;

import com.bootdo.clouddoshop.dao.AddresslistDao;
import com.bootdo.clouddoshop.domain.AddresslistDO;
import com.bootdo.clouddoshop.service.AddresslistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class AddresslistServiceImpl implements AddresslistService {
	@Autowired
	private AddresslistDao addresslistDao;
	
	@Override
	public AddresslistDO get(Integer addressid){
		return addresslistDao.get(addressid);
	}
	
	@Override
	public List<AddresslistDO> list(Map<String, Object> map){
		return addresslistDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return addresslistDao.count(map);
	}
	
	@Override
	public int save(AddresslistDO addresslist){
		return addresslistDao.save(addresslist);
	}
	
	@Override
	public int update(AddresslistDO addresslist){
		return addresslistDao.update(addresslist);
	}
	
	@Override
	public int remove(Integer addressid){
		return addresslistDao.remove(addressid);
	}
	
	@Override
	public int batchRemove(Integer[] addressids){
		return addresslistDao.batchRemove(addressids);
	}
	
}
