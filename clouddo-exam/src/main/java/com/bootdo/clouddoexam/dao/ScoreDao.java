package com.bootdo.clouddoexam.dao;

import com.bootdo.clouddoexam.domain.ScoreDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:48:34
 */
@Mapper
public interface ScoreDao {

	ScoreDO get(String studentId);
	
	List<ScoreDO> list(Map<String, Object> map);
	List<ScoreDO> listall(String grade, String classmate, String course);
	int count(Map<String, Object> map);
	
	int save(ScoreDO score);
	
	int update(ScoreDO score);
	
	int remove(String Student_ID);
	
	int batchRemove(String[] studentIds);
	List<ScoreDO> listallscore();
    List<ScoreDO> listallscorerank();
	List<ScoreDO> listallchemistryrank();
	List<ScoreDO> listallhistoryrank();
	List<ScoreDO> listallgeographyrank();
	List<ScoreDO> listallgovernmentrank();
	List<ScoreDO> listallmathematicsrank();
	List<ScoreDO> listallphysicalrank();
	List<ScoreDO> listallbiologyrank();
	List<ScoreDO> listallEnglishrank();
	List<ScoreDO> listallChineserank();
}
