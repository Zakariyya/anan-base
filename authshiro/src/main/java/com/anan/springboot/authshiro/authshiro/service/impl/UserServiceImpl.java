package com.anan.springboot.authshiro.authshiro.service.impl;

import com.urundp.common.util.MD5Utils;
import com.urundp.desktop.vda.auth.dao.UserMapper;
import com.urundp.desktop.vda.auth.entity.Role;
import com.urundp.desktop.vda.auth.entity.User;
import com.urundp.desktop.vda.auth.model.PasswordModel;
import com.urundp.desktop.vda.auth.model.UserAuthModel;
import com.urundp.desktop.vda.auth.model.UserInfoModel;
import com.urundp.desktop.vda.auth.service.UserService;
import com.urundp.desktop.vda.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserInfoModel> select() {
		return userMapper.selectAll();
	}

	@Override
	public List<UserInfoModel> userListLimit(int page,int size) {
		return userMapper.userListLimit((page-1)*size,size);
	}

	@Override
	public List<UserInfoModel> userListLimitByuserNames(String usreNames, Integer page, Integer size) {
		return userMapper.userListLimitByuserNames(usreNames,(page-1)*size,size);
		
	}
	
	@Override
	public UserInfoModel select(Integer id) {
		return userMapper.selectById(id);
	}

	@Override
	public int create(User user) {
		String salt = UUID.randomUUID().toString();
		String password = MD5Utils.MD5(user.getPassword() + salt);
		user.setSalt(salt);
		user.setPassword(password);
		user.setCreateDate(DateUtil.nowStr());
		user.setRole(Role.USER);
		return userMapper.insertNewUser(user);
	}

	@Override
	public int delete(Integer id) {
		return userMapper.delete(id);
	}

	@Override
	public int updateProperty(User user) {
		return userMapper.updateProperty(user);
	}

	@Transactional
	@Override
	public int updatePassword(PasswordModel model) {
		UserAuthModel user = userMapper.selectUserAuthModelById(model.getUserId());
		if (user.getPassword().equals(MD5Utils.MD5(model.getOldPassword() + user.getSalt()))) {
			user.setSalt(UUID.randomUUID().toString());
			user.setPassword(MD5Utils.MD5(model.getNewPassword() + user.getSalt()));
			return userMapper.updatePassword(user);
		} else {
			return 0;
		}

	}

	@Override
	public Role selectRole(Integer id) {
		return this.select(id).getRole();
	}

	@Override
	public int updateRole(Integer userId, Role role) {
		return userMapper.updateRole(userId,role);
	}

	@Override
	public UserAuthModel getByUserAuthByLoginName(String loginName) {
		return userMapper.selectUserAuthModelByLoginName(loginName);
	}

	@Override
	public Role getRoles(String loginName) {
		return this.getByUserAuthByLoginName(loginName).getRole();
	}

	@Override
	public void insert(List<User> users) {
		users.forEach(user -> this.create(user));
	}

	@Override
	public UserInfoModel select(String loginname) {
		return userMapper.selectUserInfoByLoginName(loginname);
	}

	

}
