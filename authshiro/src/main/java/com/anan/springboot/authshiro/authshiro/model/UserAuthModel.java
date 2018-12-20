package com.anan.springboot.authshiro.authshiro.model;


import com.anan.springboot.authshiro.authshiro.entity.Role;

import java.io.Serializable;

public class UserAuthModel implements Serializable{

	private static final long serialVersionUID = 7887409428580217027L;

	private Integer id;

	private String loginName;

	private String name;

	private String password;

	private String salt;

	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserAuthModel [id=" + id + ", loginName=" + loginName + ", name=" + name + ", password=" + password
				+ ", salt=" + salt + ", role=" + role + "]";
	}

}
