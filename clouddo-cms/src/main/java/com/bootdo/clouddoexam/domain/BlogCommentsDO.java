package com.bootdo.clouddoexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-09 17:04:56
 */
public class BlogCommentsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String topicId;
	//
	private String topicType;
	//
	private String content;
	//
	private String fromUid;
	//
	private String toUid;
	//
	private String avatar;
	private String title;

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
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	/**
	 * 获取：
	 */
	public String getTopicId() {
		return topicId;
	}
	/**
	 * 设置：
	 */
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	/**
	 * 获取：
	 */
	public String getTopicType() {
		return topicType;
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
	public void setFromUid(String fromUid) {
		this.fromUid = fromUid;
	}
	/**
	 * 获取：
	 */
	public String getFromUid() {
		return fromUid;
	}
	/**
	 * 设置：
	 */
	public void setToUid(String toUid) {
		this.toUid = toUid;
	}
	/**
	 * 获取：
	 */
	public String getToUid() {
		return toUid;
	}
	/**
	 * 设置：
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：
	 */
	public String getAvatar() {
		return avatar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
