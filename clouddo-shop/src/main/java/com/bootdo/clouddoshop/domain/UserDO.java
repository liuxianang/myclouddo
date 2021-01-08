package com.bootdo.clouddoshop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:10:13
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String userid;
	//
	private String username;
	//
	private String userpwd;
	//
	private String myphoto;

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
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取：
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	/**
	 * 获取：
	 */
	public String getUserpwd() {
		return userpwd;
	}
	/**
	 * 设置：
	 */
	public void setMyphoto(String myphoto) {
		this.myphoto = myphoto;
	}
	/**
	 * 获取：
	 */
	public String getMyphoto() {
		return myphoto;
	}
}
