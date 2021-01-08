package com.bootdo.clouddoapp.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-10 16:00:56
 */
public class AppshoppingcarDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String goodsid;
	//
	private String goodstype;
	//
	private String goodsname;
	//
	private Double goodsprice;
	//
	private String goodsimage;
	//
	private String storeid;
	//
	private String userphone;
	private String storename;
	//
	private String goodsnum;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	/**
	 * 获取：
	 */
	public String getGoodsid() {
		return goodsid;
	}
	/**
	 * 设置：
	 */
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
	/**
	 * 获取：
	 */
	public String getGoodstype() {
		return goodstype;
	}
	/**
	 * 设置：
	 */
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	/**
	 * 获取：
	 */
	public String getGoodsname() {
		return goodsname;
	}
	/**
	 * 设置：
	 */
	public void setGoodsprice(Double goodsprice) {
		this.goodsprice = goodsprice;
	}
	/**
	 * 获取：
	 */
	public Double getGoodsprice() {
		return goodsprice;
	}
	/**
	 * 设置：
	 */
	public void setGoodsimage(String goodsimage) {
		this.goodsimage = goodsimage;
	}
	/**
	 * 获取：
	 */
	public String getGoodsimage() {
		return goodsimage;
	}
	/**
	 * 设置：
	 */
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	/**
	 * 获取：
	 */
	public String getStoreid() {
		return storeid;
	}
	/**
	 * 设置：
	 */
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	/**
	 * 获取：
	 */
	public String getUserphone() {
		return userphone;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getGoodsnum() {
		return goodsnum;
	}

	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}
}
