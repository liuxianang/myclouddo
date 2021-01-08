package com.bootdo.clouddoadmin.domain;

import java.io.Serializable;


/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public class msgDO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
