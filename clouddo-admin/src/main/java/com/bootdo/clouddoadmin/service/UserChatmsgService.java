package com.bootdo.clouddoadmin.service;



import com.bootdo.clouddoadmin.domain.UserChatmsgDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-28 16:46:47
 */
public interface UserChatmsgService {
	
	UserChatmsgDO get(Integer id);
	
	List<UserChatmsgDO> list(Map<String, Object> map);
	List<UserChatmsgDO> top10list(Map<String, Object> map);
	List<UserChatmsgDO> top10mylist(Map<String, Object> map);
	int count(Map<String, Object> map);
	
	int save(UserChatmsgDO userChatmsg);
	
	int update(UserChatmsgDO userChatmsg);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
