package com.anan.springboot.authshiro.authshiro.orm;

import java.io.Serializable;

/**
 * 用户组
 * 
 * @author anan
 * @date Created by anan on 2018/12/20
 *
 */
public class Usergroup implements Serializable {

	private static final long serialVersionUID = 7632400150459759810L;

	/**
	 * id
	 */
	private long id;

	/**
	 * 组名
	 */
	private String name;

	/**
	 * 上级组id
	 */
	private long pId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

}
