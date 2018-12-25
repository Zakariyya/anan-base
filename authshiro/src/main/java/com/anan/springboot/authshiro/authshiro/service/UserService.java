package com.anan.springboot.authshiro.authshiro.service;

import com.anan.springboot.authshiro.authshiro.orm.Role;
import com.anan.springboot.authshiro.authshiro.orm.User;
import com.anan.springboot.authshiro.authshiro.model.PasswordModel;
import com.anan.springboot.authshiro.authshiro.model.UserAuthModel;
import com.anan.springboot.authshiro.authshiro.model.UserInfoModel;
import lombok.Data;

import java.util.List;

public interface UserService {

	/**
	 * 查询所有
	 *
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public List<UserInfoModel> select();
	
	public List<UserInfoModel> userListLimit(int page, int size);

	/**
	 * 通过主键查询
	 *
	 * @param id
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public UserInfoModel select(Integer id);
	
	public UserInfoModel select(String loginname);
	
	public List<UserInfoModel> userListLimitByuserNames(String userNames, Integer page, Integer size);

	/**
	 * 插入用户
	 *
	 * @param user
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public int create(User user);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public int delete(Integer id);

	/**
	 * 更新属性
	 *
	 * @param user
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public int updateProperty(User user);

	/**
	 * 更新密码
	 *
	 * @param user
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public int updatePassword(PasswordModel model);

	/**
	 * 查询角色
	 *
	 * @param id
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public Role selectRole(Integer id);

	/**
	 * 调整角色
	 *
	 * @param id
	 * @param model
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public int updateRole(Integer userId, Role role);

	/**
	 * 获取用户权限信息
	 *
	 * @param primaryPrincipal
	 * @return
	 * @author anan
   * @date Created by anan on 2018/12/20

   */
	public UserAuthModel getByUserAuthByLoginName(String loginName);

	public Role getRoles(String loginName);

	public void insert(List<User> users);

}
