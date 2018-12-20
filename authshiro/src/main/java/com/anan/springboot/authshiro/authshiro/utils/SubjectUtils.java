package com.anan.springboot.authshiro.authshiro.utils;

import com.urundp.common.exception.SessionTimeoutException;
import com.urundp.desktop.vda.auth.model.UserInfoModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SubjectUtils {

	public static UserInfoModel getProfile() {
		return getProfile(SecurityUtils.getSubject());
	}

	public static UserInfoModel getProfile(Subject subject) {
		UserInfoModel userInfoModel = null;
		try {
			userInfoModel = (UserInfoModel) subject.getSession().getAttribute("user");
		} catch (Exception e) {
			throw new SessionTimeoutException();
		}
		if (userInfoModel == null) {
			throw new SessionTimeoutException();
		}
		return userInfoModel;
	}

	public static String getUsername() {
		return getUsername(SecurityUtils.getSubject());
	}

	public static String getUsername(Subject subject) {
		return getProfile(subject).getLoginName();
	}
}
