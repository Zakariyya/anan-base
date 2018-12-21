package com.anan.springboot.authshiro.authshiro.orm;

import java.io.Serializable;

public class UsergroupUser implements Serializable {

	private static final long serialVersionUID = -2512204417919546560L;
	/**
	 * 用户组id
	 */
	private long usergroupId;

	/**
	 * 用户名
	 */
	private String user;

	public UsergroupUser() {
		super();
	}

	public UsergroupUser(long usergroupId, String user) {
		super();
		this.usergroupId = usergroupId;
		this.user = user;
	}

	public long getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(long usergroupId) {
		this.usergroupId = usergroupId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
