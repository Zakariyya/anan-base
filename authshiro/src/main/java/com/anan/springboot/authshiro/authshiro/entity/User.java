package com.anan.springboot.authshiro.authshiro.entity;

public class User {

	private Integer id;

	private String loginName;

	private String name;

	private String password;

	private String salt;

	private String birthday;

	private Short gender;

	private String email;

	private String phone;

	/**
	 * 建立时间
	 */
	private String createDate;

	/**
	 * 登录次数 
	 */
	private Integer loginCount;

	/**
	 * 上次访问时间
	 */
	private String previousVisit;

	/**
	 * 最后登录时间
	 */
	private String lastVisit;

	private Role role;



	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", name=" + name + ", birthday=" + birthday + ", gender="
				+ gender + ", email=" + email + ", phone=" + phone + ", createDate=" + createDate + ", loginCount="
				+ loginCount + ", previousVisit=" + previousVisit + ", lastVisit=" + lastVisit + "]";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}