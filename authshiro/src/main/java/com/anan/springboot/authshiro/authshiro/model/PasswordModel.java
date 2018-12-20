package com.anan.springboot.authshiro.authshiro.model;

import java.io.Serializable;

public class PasswordModel implements Serializable {

	private static final long serialVersionUID = -3189844608760763147L;

	private Integer userId;

	private String oldPassword;

	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
