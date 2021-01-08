package com.bootdo.clouddoapp.service.impl;

import com.bootdo.clouddoapp.dao.imageDao;

import com.bootdo.clouddoapp.domain.imageDTO;
import com.bootdo.clouddoapp.service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ImageServiceImpl implements imageService {
	@Autowired
	private imageDao fileDao;
	
	@Override
	public imageDTO get(Long id){
		return fileDao.get(id);
	}
	
	@Override
	public List<imageDTO> list(Map<String, Object> map){
		return fileDao.list(map);
	}
	@Override
	public List<imageDTO> listall(){
		return fileDao.listall();
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fileDao.count(map);
	}
	
	@Override
	public int save(imageDTO file){
		return fileDao.save(file);
	}
	
	@Override
	public int update(imageDTO file){
		return fileDao.update(file);
	}
	
	@Override
	public int remove(Long id){
		return fileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return fileDao.batchRemove(ids);
	}
	
}
