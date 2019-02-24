package anan.base.rbac.service.impl;

import anan.base.core.orm.ResponseResult;
import anan.base.core.service.BaseService;
import anan.base.rbac.orm.Role;
import anan.base.rbac.orm.User;
import anan.base.rbac.orm.UserAndRole;
import anan.base.rbac.repository.UserAndRoleRepository;
import anan.base.rbac.service.UserAndRoleService;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:53
 */
@Service
@Transactional
public class UserAndRoleServiceImpl implements UserAndRoleService {

  @Autowired
  private UserAndRoleRepository repository;

  @Autowired
  private BaseService baseService;

  @Override
  public List<UserAndRole> findAll() {
    return repository.findAll();
  }

  @Override
  public UserAndRole findOne(Integer id) {
    return repository.findById(id).get();
  }

  @Transactional
  @Override
  public UserAndRole save(UserAndRole data) {
    data.setId(null);
    return this.update(data);
  }

  @Transactional
  @Override
  public UserAndRole update(UserAndRole data) {
//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    data.setPassword(encoder.encode(data.getPassword()));
    return repository.save(data);
  }


  @Override
  public ResponseResult delete(String id, ResponseResult result) {
    return baseService.delete(id, repository, result);
  }

  /**********
   * by user
   */
  @Override
  public Set<Role> findRoleByUser(User user) {
    var list = repository.findAllByUserId(user.getId());
    var roleList = new HashSet();
    for(var item :list){
      roleList.add(item.getRole());
    }
    return roleList;
  }

  @Override
  public Set<User> findUserByRole(Role role) {
    var list = repository.findAllByRoleId(role.getId());
    var userList = new HashSet();
    for(var item :list){
      userList.add(item.getUser());
    }
    return userList;
  }
}
