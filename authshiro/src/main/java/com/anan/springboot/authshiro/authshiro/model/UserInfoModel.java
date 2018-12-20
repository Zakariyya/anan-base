package com.anan.springboot.authshiro.authshiro.model;


import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author anan
 * @date Created by anan on 2018/12/20
 *
 */
public class UserInfoModel implements Serializable {

	private static final long serialVersionUID = -2756042951383125828L;

	private Integer id;

	private String loginName;

	private String name;

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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getPreviousVisit() {
		return previousVisit;
	}

	public void setPreviousVisit(String previousVisit) {
		this.previousVisit = previousVisit;
	}

	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfoModel [id=" + id + ", loginName=" + loginName + ", name=" + name + ", birthday=" + birthday
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", createDate=" + createDate
				+ ", loginCount=" + loginCount + ", previousVisit=" + previousVisit + ", lastVisit=" + lastVisit
				+ ", role=" + role + "]";
	}

}
