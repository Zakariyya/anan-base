package anan.base.auth.service;


import anan.base.auth.orm.Role;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2018/12/27 12:15
 */
public interface RoleService {

  List<Role> findAll();

  Role findOne(Integer id);

  Role save(Role data);

  Role update(Role data);

  void delete(Integer id);
}
