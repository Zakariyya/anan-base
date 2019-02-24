package anan.base.rbac.service.impl;

import anan.base.core.orm.ResponseResult;
import anan.base.core.service.BaseService;
import anan.base.rbac.orm.Menu;
import anan.base.rbac.orm.MenuAndRole;
import anan.base.rbac.orm.Role;
import anan.base.rbac.repository.MenuAndRoleRepository;
import anan.base.rbac.service.MenuAndRoleService;
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
public class MenuAndRoleServiceImpl implements MenuAndRoleService {

  @Autowired
  private MenuAndRoleRepository repository;

  @Autowired
  private BaseService baseService;

  @Override
  public List<MenuAndRole> findAll() {
    return repository.findAll();
  }

  @Override
  public MenuAndRole findOne(Integer id) {
    return repository.findById(id).get();
  }

  @Transactional
  @Override
  public MenuAndRole save(MenuAndRole data) {
    data.setId(null);
    return this.update(data);
  }

  @Transactional
  @Override
  public MenuAndRole update(MenuAndRole data) {
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
  public Set<Role> findRoleByMenu(Menu data) {
    var list = repository.findAllByMenuId(data.getId());
    var roleList = new HashSet();
    for(var item :list){
      roleList.add(item.getRole());
    }
    return roleList;
  }

  @Override
  public Set<Menu> findMenuByRole(Role data) {
    var list = repository.findAllByRoleId(data.getId());
    var menuList = new HashSet();
    for(var item :list){
      menuList.add(item.getMenuId());
    }
    return menuList;
  }
}
