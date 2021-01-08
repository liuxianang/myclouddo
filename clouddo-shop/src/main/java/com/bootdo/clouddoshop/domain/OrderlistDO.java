package com.bootdo.clouddoshop.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-10 21:05:42
 */
public class OrderlistDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String orderid;
	//
	private String userid;
	//
	private Integer productid;
	//
	private String productname;
	//
	private Integer productprice;
	//
	private Integer productnum;
	//
	private String productimg;
	//
	private String totalprice;
	//
	private String streetname;
	//
	private String postname;
	//
	private String postcode;
	//
	private String tel;
	//
	private String itemprice;
	//折扣
	private String discount;
	//配送费
	private String shipprice;
	//运费险
	private String freightrisk;
	//
	private String createdate;
	//
	private Integer ifpay;

	/**
	 * 设置：
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：
	 */
	public String getOrderid() {
		return orderid;
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
	public void setPostname(String postname) {
		this.postname = postname;
	}
	/**
	 * 获取：
	 */
	public String getPostname() {
		return postname;
	}
	/**
	 * 设置：
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * 获取：
	 */
	public String getPostcode() {
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
	public void setItemprice(String itemprice) {
		this.itemprice = itemprice;
	}
	/**
	 * 获取：
	 */
	public String getItemprice() {
		return itemprice;
	}
	/**
	 * 设置：折扣
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * 获取：折扣
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * 设置：配送费
	 */
	public void setShipprice(String shipprice) {
		this.shipprice = shipprice;
	}
	/**
	 * 获取：配送费
	 */
	public String getShipprice() {
		return shipprice;
	}
	/**
	 * 设置：运费险
	 */
	public void setFreightrisk(String freightrisk) {
		this.freightrisk = freightrisk;
	}
	/**
	 * 获取：运费险
	 */
	public String getFreightrisk() {
		return freightrisk;
	}
	/**
	 * 设置：
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：
	 */
	public String getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：
	 */
	public void setIfpay(Integer ifpay) {
		this.ifpay = ifpay;
	}
	/**
	 * 获取：
	 */
	public Integer getIfpay() {
		return ifpay;
	}
}
