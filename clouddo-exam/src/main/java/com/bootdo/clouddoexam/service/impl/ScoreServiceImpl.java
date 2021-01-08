package com.bootdo.clouddoexam.service.impl;

import com.bootdo.clouddoexam.dao.ScoreDao;
import com.bootdo.clouddoexam.domain.ScoreDO;
import com.bootdo.clouddoexam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreDao scoreDao;
	
	@Override
	public ScoreDO get(String studentId){
		return scoreDao.get(studentId);
	}
	
	@Override
	public List<ScoreDO> list(Map<String, Object> map){
		return scoreDao.list(map);
	}

	@Override
	public List<ScoreDO> listall(String grade,String classmate,String course) {
		return scoreDao.listall(grade,classmate, course);
	}

	@Override
	public int count(Map<String, Object> map){
		return scoreDao.count(map);
	}
	
	@Override
	public int save(ScoreDO score){
		return scoreDao.save(score);
	}
	
	@Override
	public int update(ScoreDO score){
		return scoreDao.update(score);
	}
	
	@Override
	public int remove(String studentId){
		return scoreDao.remove(studentId);
	}
	
	@Override
	public int batchRemove(String[] studentIds){
		return scoreDao.batchRemove(studentIds);
	}

	@Override
	public List<ScoreDO> listallscore() {
		return scoreDao.listallscore();
	}

    @Override
    public List<ScoreDO> listallscorerank() {
        return scoreDao.listallscorerank();
    }

	@Override
	public List<ScoreDO> listallchemistryrank() {
		return scoreDao.listallchemistryrank();
	}

	@Override
	public List<ScoreDO> listallhistoryrank() {
		return scoreDao.listallhistoryrank();
	}

	@Override
	public List<ScoreDO> listallgeographyrank() {
		return scoreDao.listallgeographyrank();
	}

	@Override
	public List<ScoreDO> listallgovernmentrank() {
		return scoreDao.listallgovernmentrank();
	}

	@Override
	public List<ScoreDO> listallmathematicsrank() {
		return scoreDao.listallmathematicsrank();
	}

	@Override
	public List<ScoreDO> listallphysicalrank() {
		return scoreDao.listallphysicalrank();
	}

	@Override
	public List<ScoreDO> listallbiologyrank() {
		return scoreDao.listallbiologyrank();
	}

	@Override
	public List<ScoreDO> listallEnglishrank() {
		return scoreDao.listallEnglishrank();
	}

	@Override
	public List<ScoreDO> listallChineserank() {
		return scoreDao.listallChineserank();
	}

}
