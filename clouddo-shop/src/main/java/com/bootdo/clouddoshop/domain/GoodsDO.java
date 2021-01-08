package com.bootdo.clouddoshop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:02:24
 */
public class GoodsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer productid;
	//
	private String productname;
	//
	private Integer productprice;
	//
	private String checked;
	//
	private Integer productnum;
	//
	private String productimg;
	//
	private String subTitle;
	//
	private Integer limitNum;
	//
	private String desc;
	//
	private String descimg;
	//
	private String productdetails;


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
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	/**
	 * 获取：
	 */
	public Integer getProductid() {
		return productid;
	}
	/**
	 * 设置：
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	/**
	 * 获取：
	 */
	public String getProductname() {
		return productname;
	}
	/**
	 * 设置：
	 */
	public void setProductprice(Integer productprice) {
		this.productprice = productprice;
	}
	/**
	 * 获取：
	 */
	public Integer getProductprice() {
		return productprice;
	}
	/**
	 * 设置：
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}
	/**
	 * 获取：
	 */
	public String getChecked() {
		return checked;
	}
	/**
	 * 设置：
	 */
	public void setProductnum(Integer productnum) {
		this.productnum = productnum;
	}
	/**
	 * 获取：
	 */
	public Integer getProductnum() {
		return productnum;
	}
	/**
	 * 设置：
	 */
	public void setProductimg(String productimg) {
		this.productimg = productimg;
	}
	/**
	 * 获取：
	 */
	public String getProductimg() {
		return productimg;
	}
	/**
	 * 设置：
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	/**
	 * 获取：
	 */
	public String getSubTitle() {
		return subTitle;
	}
	/**
	 * 设置：
	 */
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	/**
	 * 获取：
	 */
	public Integer getLimitNum() {
		return limitNum;
	}
	/**
	 * 设置：
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：
	 */
	public void setDescimg(String descimg) {
		this.descimg = descimg;
	}
	/**
	 * 获取：
	 */
	public String getDescimg() {
		return descimg;
	}
	/**
	 * 设置：
	 */
	public void setProductdetails(String productdetails) {
		this.productdetails = productdetails;
	}
	/**
	 * 获取：
	 */
	public String getProductdetails() {
		return productdetails;
	}

}
