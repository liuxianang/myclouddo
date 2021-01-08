package com.bootdo.clouddoexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-02 12:56:54
 */
public class PaperDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private String description;
	//图标
	private String cover;
	//发布者
	private String author;
	//
	private Date created;
	//
	private Date startTime;
	//
	private Date endTime;
	//
	private String subjectName;
	//
	private String publish;
	//
	private String type;
	//
	private String remark;
	//
	private String questionallId;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：图标
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}
	/**
	 * 获取：图标
	 */
	public String getCover() {
		return cover;
	}
	/**
	 * 设置：发布者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：发布者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * 获取：
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * 设置：
	 */
	public void setPublish(String publish) {
		this.publish = publish;
	}
	/**
	 * 获取：
	 */
	public String getPublish() {
		return publish;
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
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setQuestionallId(String questionallId) {
		this.questionallId = questionallId;
	}
	/**
	 * 获取：
	 */
	public String getQuestionallId() {
		return questionallId;
	}
}
