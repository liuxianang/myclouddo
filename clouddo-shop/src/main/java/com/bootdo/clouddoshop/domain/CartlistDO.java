package com.bootdo.clouddoshop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 20:58:19
 */
public class CartlistDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer cartid;
	//
	private String userid;
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
	private String totalprice;

	/**
	 * 设置：
	 */
	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}
	/**
	 * 获取：
	 */
	public Integer getCartid() {
		return cartid;
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
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	/**
	 * 获取：
	 */
	public String getTotalprice() {
		return totalprice;
	}
}
