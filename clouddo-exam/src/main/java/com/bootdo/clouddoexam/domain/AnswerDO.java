package com.bootdo.clouddoexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-02 18:01:36
 */
public class AnswerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String testPaperid;
	//
	private String testPapername;
	//
	private String studentid;
	//
	private String studentname;
	//
	private String singleChoiceAnswer;
	//
	private String singleyorn;
	//
	private String singlescore;
	//
	private String multipleChoiceAnswer;
	//
	private String multipleyorn;
	//
	private String multiplescore;
	//
	private String shortAnswer1;
	//
	private String answer1yorn;
	//
	private String answer1score;
	//
	private String shortAnswer2;
	//
	private String answer2yorn;
	//
	private String answer2score;
	//
	private String shortAnswer3;
	//
	private String answer3yorn;
	//
	private String answer3score;
	//
	private String shortAnswer4;
	//
	private String answer4yorn;
	//
	private String answer4score;
	//
	private String shortAnswer5;
	//
	private String answer5yorn;
	//
	private String answer5score;
	//
	private String totalscore;
	//批改状态
	private Integer state;

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
	public void setTestPaperid(String testPaperid) {
		this.testPaperid = testPaperid;
	}
	/**
	 * 获取：
	 */
	public String getTestPaperid() {
		return testPaperid;
	}
	/**
	 * 设置：
	 */
	public void setTestPapername(String testPapername) {
		this.testPapername = testPapername;
	}
	/**
	 * 获取：
	 */
	public String getTestPapername() {
		return testPapername;
	}
	/**
	 * 设置：
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * 设置：
	 */
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	/**
	 * 获取：
	 */
	public String getStudentname() {
		return studentname;
	}
	/**
	 * 设置：
	 */
	public void setSingleChoiceAnswer(String singleChoiceAnswer) {
		this.singleChoiceAnswer = singleChoiceAnswer;
	}
	/**
	 * 获取：
	 */
	public String getSingleChoiceAnswer() {
		return singleChoiceAnswer;
	}
	/**
	 * 设置：
	 */
	public void setSingleyorn(String singleyorn) {
		this.singleyorn = singleyorn;
	}
	/**
	 * 获取：
	 */
	public String getSingleyorn() {
		return singleyorn;
	}
	/**
	 * 设置：
	 */
	public void setSinglescore(String singlescore) {
		this.singlescore = singlescore;
	}
	/**
	 * 获取：
	 */
	public String getSinglescore() {
		return singlescore;
	}
	/**
	 * 设置：
	 */
	public void setMultipleChoiceAnswer(String multipleChoiceAnswer) {
		this.multipleChoiceAnswer = multipleChoiceAnswer;
	}
	/**
	 * 获取：
	 */
	public String getMultipleChoiceAnswer() {
		return multipleChoiceAnswer;
	}
	/**
	 * 设置：
	 */
	public void setMultipleyorn(String multipleyorn) {
		this.multipleyorn = multipleyorn;
	}
	/**
	 * 获取：
	 */
	public String getMultipleyorn() {
		return multipleyorn;
	}
	/**
	 * 设置：
	 */
	public void setMultiplescore(String multiplescore) {
		this.multiplescore = multiplescore;
	}
	/**
	 * 获取：
	 */
	public String getMultiplescore() {
		return multiplescore;
	}
	/**
	 * 设置：
	 */
	public void setShortAnswer1(String shortAnswer1) {
		this.shortAnswer1 = shortAnswer1;
	}
	/**
	 * 获取：
	 */
	public String getShortAnswer1() {
		return shortAnswer1;
	}
	/**
	 * 设置：
	 */
	public void setAnswer1yorn(String answer1yorn) {
		this.answer1yorn = answer1yorn;
	}
	/**
	 * 获取：
	 */
	public String getAnswer1yorn() {
		return answer1yorn;
	}
	/**
	 * 设置：
	 */
	public void setAnswer1score(String answer1score) {
		this.answer1score = answer1score;
	}
	/**
	 * 获取：
	 */
	public String getAnswer1score() {
		return answer1score;
	}
	/**
	 * 设置：
	 */
	public void setShortAnswer2(String shortAnswer2) {
		this.shortAnswer2 = shortAnswer2;
	}
	/**
	 * 获取：
	 */
	public String getShortAnswer2() {
		return shortAnswer2;
	}
	/**
	 * 设置：
	 */
	public void setAnswer2yorn(String answer2yorn) {
		this.answer2yorn = answer2yorn;
	}
	/**
	 * 获取：
	 */
	public String getAnswer2yorn() {
		return answer2yorn;
	}
	/**
	 * 设置：
	 */
	public void setAnswer2score(String answer2score) {
		this.answer2score = answer2score;
	}
	/**
	 * 获取：
	 */
	public String getAnswer2score() {
		return answer2score;
	}
	/**
	 * 设置：
	 */
	public void setShortAnswer3(String shortAnswer3) {
		this.shortAnswer3 = shortAnswer3;
	}
	/**
	 * 获取：
	 */
	public String getShortAnswer3() {
		return shortAnswer3;
	}
	/**
	 * 设置：
	 */
	public void setAnswer3yorn(String answer3yorn) {
		this.answer3yorn = answer3yorn;
	}
	/**
	 * 获取：
	 */
	public String getAnswer3yorn() {
		return answer3yorn;
	}
	/**
	 * 设置：
	 */
	public void setAnswer3score(String answer3score) {
		this.answer3score = answer3score;
	}
	/**
	 * 获取：
	 */
	public String getAnswer3score() {
		return answer3score;
	}
	/**
	 * 设置：
	 */
	public void setShortAnswer4(String shortAnswer4) {
		this.shortAnswer4 = shortAnswer4;
	}
	/**
	 * 获取：
	 */
	public String getShortAnswer4() {
		return shortAnswer4;
	}
	/**
	 * 设置：
	 */
	public void setAnswer4yorn(String answer4yorn) {
		this.answer4yorn = answer4yorn;
	}
	/**
	 * 获取：
	 */
	public String getAnswer4yorn() {
		return answer4yorn;
	}
	/**
	 * 设置：
	 */
	public void setAnswer4score(String answer4score) {
		this.answer4score = answer4score;
	}
	/**
	 * 获取：
	 */
	public String getAnswer4score() {
		return answer4score;
	}
	/**
	 * 设置：
	 */
	public void setShortAnswer5(String shortAnswer5) {
		this.shortAnswer5 = shortAnswer5;
	}
	/**
	 * 获取：
	 */
	public String getShortAnswer5() {
		return shortAnswer5;
	}
	/**
	 * 设置：
	 */
	public void setAnswer5yorn(String answer5yorn) {
		this.answer5yorn = answer5yorn;
	}
	/**
	 * 获取：
	 */
	public String getAnswer5yorn() {
		return answer5yorn;
	}
	/**
	 * 设置：
	 */
	public void setAnswer5score(String answer5score) {
		this.answer5score = answer5score;
	}
	/**
	 * 获取：
	 */
	public String getAnswer5score() {
		return answer5score;
	}
	/**
	 * 设置：
	 */
	public void setTotalscore(String totalscore) {
		this.totalscore = totalscore;
	}
	/**
	 * 获取：
	 */
	public String getTotalscore() {
		return totalscore;
	}
	/**
	 * 设置：批改状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：批改状态
	 */
	public Integer getState() {
		return state;
	}
}
