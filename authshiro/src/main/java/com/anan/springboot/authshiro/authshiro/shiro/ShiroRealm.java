package com.anan.springboot.authshiro.authshiro.shiro;

import com.urundp.common.util.MD5Utils;
import com.urundp.desktop.vda.auth.model.UserAuthModel;
import com.urundp.desktop.vda.auth.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroRealm extends AuthorizingRealm {

	private static Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		UserAuthModel user = userService.getByUserAuthByLoginName((String) principals.getPrimaryPrincipal());

		// 把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),
				SecurityUtils.getSubject().getPrincipals());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 设置角色
		info.addRole(user.getRole().toString());

		return info;

	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String userName = token.getUsername();

		UserAuthModel user = userService.getByUserAuthByLoginName(userName);
		if (user == null) {
			throw new AuthenticationException("username or password error.");
		}
		if (user.getPassword().equals(MD5Utils.MD5(String.valueOf(token.getPassword()) + user.getSalt()))) {
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", userService.select(user.getId()));
			return new SimpleAuthenticationInfo(userName, token.getPassword(), getName());
		} else {
			throw new AuthenticationException("username or password error.");
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
