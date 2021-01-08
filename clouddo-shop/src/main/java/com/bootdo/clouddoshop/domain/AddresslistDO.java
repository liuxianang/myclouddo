package com.bootdo.clouddoshop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 20:18:04
 */
public class AddresslistDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer addressid;
	//
	private String userid;
	//
	private String username;
	//
	private String streetname;
	//
	private Integer postcode;
	//
	private String tel;
	//
	private Integer isdefault;

	/**
	 * 设置：
	 */
	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}
	/**
	 * 获取：
	 */
	public Integer getAddressid() {
		return addressid;
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
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	/**
	 * 获取：
	 */
	public String getStreetname() {
		return streetname;
	}
	/**
	 * 设置：
	 */
	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}
	/**
	 * 获取：
	 */
	public Integer getPostcode() {
		return postcode;
	}
	/**
	 * 设置：
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：
	 */
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}
	/**
	 * 获取：
	 */
	public Integer getIsdefault() {
		return isdefault;
	}
}
