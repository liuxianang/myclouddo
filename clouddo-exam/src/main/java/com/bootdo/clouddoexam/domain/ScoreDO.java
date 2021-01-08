package com.bootdo.clouddoexam.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:48:34
 */
public class ScoreDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String studentId;
	//
	private String studentName;
	//
	private String stydentGrade;
	//
	private Integer chemistry;
	//
	private Integer history;
	//
	private Integer geography;
	//
	private Integer government;
	//
	private Integer mathematics;
	//
	private Integer physical;
	//
	private Integer biology;
	//
	private Double english;
	//
	private Double chinese;
	private Double total_score;
	private Integer rownum;
	private Integer total_score_rank;
	private Integer chemistry_rank;
	private Integer history_rank;
	private Integer geography_rank;
	private Integer government_rank;
	private Integer mathematics_rank;
	private Integer physical_rank;
	private Integer biology_rank;
	private Integer English_rank;
	private Integer Chinese_rank;


	/**
	 * 设置：
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * 获取：
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * 设置：
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * 获取：
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 设置：
	 */
	public void setStydentGrade(String stydentGrade) {
		this.stydentGrade = stydentGrade;
	}
	/**
	 * 获取：
	 */
	public String getStydentGrade() {
		return stydentGrade;
	}
	/**
	 * 设置：
	 */
	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}
	/**
	 * 获取：
	 */
	public Integer getChemistry() {
		return chemistry;
	}
	/**
	 * 设置：
	 */
	public void setHistory(Integer history) {
		this.history = history;
	}
	/**
	 * 获取：
	 */
	public Integer getHistory() {
		return history;
	}
	/**
	 * 设置：
	 */
	public void setGeography(Integer geography) {
		this.geography = geography;
	}
	/**
	 * 获取：
	 */
	public Integer getGeography() {
		return geography;
	}
	/**
	 * 设置：
	 */
	public void setGovernment(Integer government) {
		this.government = government;
	}
	/**
	 * 获取：
	 */
	public Integer getGovernment() {
		return government;
	}
	/**
	 * 设置：
	 */
	public void setMathematics(Integer mathematics) {
		this.mathematics = mathematics;
	}
	/**
	 * 获取：
	 */
	public Integer getMathematics() {
		return mathematics;
	}
	/**
	 * 设置：
	 */
	public void setPhysical(Integer physical) {
		this.physical = physical;
	}
	/**
	 * 获取：
	 */
	public Integer getPhysical() {
		return physical;
	}
	/**
	 * 设置：
	 */
	public void setBiology(Integer biology) {
		this.biology = biology;
	}
	/**
	 * 获取：
	 */
	public Integer getBiology() {
		return biology;
	}
	/**
	 * 设置：
	 */
	public void setEnglish(Double english) {
		this.english = english;
	}
	/**
	 * 获取：
	 */
	public Double getEnglish() {
		return english;
	}
	/**
	 * 设置：
	 */
	public void setChinese(Double chinese) {
		this.chinese = chinese;
	}
	/**
	 * 获取：
	 */
	public Double getChinese() {
		return chinese;
	}

	public Double getTotal_score() {
		total_score=0d;
		if(chemistry!=null){
			total_score=total_score+chemistry;
		}
		if(history!=null){
			total_score=total_score+history;
		}if(geography!=null){
			total_score=total_score+geography;
		}if(government!=null){
			total_score=total_score+government;
		}if(mathematics!=null){
			total_score=total_score+mathematics;
		}if(physical!=null){
			total_score=total_score+physical;
		}
		if(biology!=null){
			total_score=total_score+biology;
		}
		if(english!=null){
			total_score=total_score+english;
		}
		if(chinese!=null){
			total_score=total_score+chinese;
		}
		return total_score;



	}
	public void setTotal_score(Double total_score) {
		this.total_score = total_score;
	}


	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public Integer getTotal_score_rank() {
		return total_score_rank;
	}

	public void setTotal_score_rank(Integer total_score_rank) {
		this.total_score_rank = total_score_rank;
	}

	public Integer getChemistry_rank() {
		return chemistry_rank;
	}

	public void setChemistry_rank(Integer chemistry_rank) {
		this.chemistry_rank = chemistry_rank;
	}

	public Integer getHistory_rank() {
		return history_rank;
	}

	public void setHistory_rank(Integer history_rank) {
		this.history_rank = history_rank;
	}

	public Integer getGeography_rank() {
		return geography_rank;
	}

	public void setGeography_rank(Integer geography_rank) {
		this.geography_rank = geography_rank;
	}

	public Integer getGovernment_rank() {
		return government_rank;
	}

	public void setGovernment_rank(Integer government_rank) {
		this.government_rank = government_rank;
	}

	public Integer getMathematics_rank() {
		return mathematics_rank;
	}

	public void setMathematics_rank(Integer mathematics_rank) {
		this.mathematics_rank = mathematics_rank;
	}

	public Integer getPhysical_rank() {
		return physical_rank;
	}

	public void setPhysical_rank(Integer physical_rank) {
		this.physical_rank = physical_rank;
	}

	public Integer getBiology_rank() {
		return biology_rank;
	}

	public void setBiology_rank(Integer biology_rank) {
		this.biology_rank = biology_rank;
	}

	public Integer getEnglish_rank() {
		return English_rank;
	}

	public void setEnglish_rank(Integer english_rank) {
		English_rank = english_rank;
	}

	public Integer getChinese_rank() {
		return Chinese_rank;
	}

	public void setChinese_rank(Integer chinese_rank) {
		Chinese_rank = chinese_rank;
	}
}
