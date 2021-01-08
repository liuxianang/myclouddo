package com.bootdo.clouddoadmin.service.impl;

import com.bootdo.clouddoadmin.dao.UserChatmsgDao;
import com.bootdo.clouddoadmin.domain.UserChatmsgDO;
import com.bootdo.clouddoadmin.service.UserChatmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service
public class UserChatmsgServiceImpl implements UserChatmsgService {
	@Autowired
	private UserChatmsgDao userChatmsgDao;
	
	@Override
	public UserChatmsgDO get(Integer id){
		return userChatmsgDao.get(id);
	}

	@Override
	public List<UserChatmsgDO> list(Map<String, Object> map){
		return userChatmsgDao.list(map);
	}
	@Override
	public List<UserChatmsgDO> top10list(Map<String, Object> map){
		return userChatmsgDao.top10list(map);
	}
	@Override
	public List<UserChatmsgDO> top10mylist(Map<String, Object> map){
		return userChatmsgDao.top10mylist(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userChatmsgDao.count(map);
	}
	
	@Override
	public int save(UserChatmsgDO userChatmsg){
		return userChatmsgDao.save(userChatmsg);
	}
	
	@Override
	public int update(UserChatmsgDO userChatmsg){
		return userChatmsgDao.update(userChatmsg);
	}
	
	@Override
	public int remove(Integer id){
		return userChatmsgDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userChatmsgDao.batchRemove(ids);
	}
	
}
