package anan.base.rbac.service;


import anan.base.core.orm.ResponseResult;
import anan.base.rbac.orm.Menu;
import anan.base.rbac.orm.MenuAndRole;
import anan.base.rbac.orm.Role;

import java.util.List;
import java.util.Set;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:38
 */
public interface MenuAndRoleService {


  List<MenuAndRole> findAll();

  MenuAndRole findOne(Integer id);

  MenuAndRole save(MenuAndRole data);

  MenuAndRole update(MenuAndRole data) ;

  ResponseResult delete(String id, ResponseResult result);

  /***
   * by user
   */
  Set<Role> findRoleByMenu(Menu menu);

  Set<Menu> findMenuByRole(Role role);

}
