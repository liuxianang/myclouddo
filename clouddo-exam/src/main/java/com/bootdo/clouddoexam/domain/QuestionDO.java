package com.bootdo.clouddoexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-16 16:07:43
 */
public class QuestionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String type;
	//
	private String content;
	//
	private Integer score;
	private Integer winscore;
	//
	private String answer;
	private String Modeanswer;//额外的字段标准答案批改显示用
	private String yorN;//额外的字段答对标识批改显示用
	private String[] answerduo;
	//
	private String analysis;
	//
	private String optionA;
	//
	private String optionB;
	//
	private String optionC;
	//
	private String optionD;
	//
	private String title;
	//
	private String simpleTitle;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * 获取：
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * 设置：
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * 获取：
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * 设置：
	 */
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	/**
	 * 获取：
	 */
	public String getAnalysis() {
		return analysis;
	}
	/**
	 * 设置：
	 */
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	/**
	 * 获取：
	 */
	public String getOptionA() {
		return optionA;
	}
	/**
	 * 设置：
	 */
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	/**
	 * 获取：
	 */
	public String getOptionB() {
		return optionB;
	}
	/**
	 * 设置：
	 */
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	/**
	 * 获取：
	 */
	public String getOptionC() {
		return optionC;
	}
	/**
	 * 设置：
	 */
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	/**
	 * 获取：
	 */
	public String getOptionD() {
		return optionD;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setSimpleTitle(String simpleTitle) {
		this.simpleTitle = simpleTitle;
	}
	/**
	 * 获取：
	 */
	public String getSimpleTitle() {
		return simpleTitle;
	}

	public String[] getAnswerduo() {
		return answerduo;
	}

	public void setAnswerduo(String[] answerduo) {
		this.answerduo = answerduo;
	}

	public String getModeanswer() {
		return Modeanswer;
	}

	public void setModeanswer(String modeanswer) {
		Modeanswer = modeanswer;
	}

	public Integer getWinscore() {
		return winscore;
	}

	public void setWinscore(Integer winscore) {
		this.winscore = winscore;
	}


	public String getYorN() {
		return yorN;
	}

	public void setYorN(String yorN) {
		this.yorN = yorN;
	}
}
