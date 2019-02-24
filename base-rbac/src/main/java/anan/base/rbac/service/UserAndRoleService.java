package anan.base.rbac.service;

import anan.base.core.orm.ResponseResult;
import anan.base.rbac.orm.Role;
import anan.base.rbac.orm.User;
import anan.base.rbac.orm.UserAndRole;

import java.util.List;
import java.util.Set;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:38
 */
public interface UserAndRoleService {


  List<UserAndRole> findAll();

  UserAndRole findOne(Integer id);

  UserAndRole save(UserAndRole data);

  UserAndRole update(UserAndRole data) ;

  ResponseResult delete(String id, ResponseResult result);

  /***
   * by user
   */
  Set<Role> findRoleByUser(User user);

  Set<User> findUserByRole(Role role);

}
