package anan.base.auth.service.impl;

import anan.base.auth.orm.Role;
import anan.base.auth.service.RoleService;
import anan.base.auth.repository.RoleRepository;
import anan.base.core.orm.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2018/12/27 14:26
 */
@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;


  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public Role findOne(Integer id) {
    return roleRepository.getOne(id);
  }

  @Override
  public Role save(Role data) {
    data.setId(null);
    return roleRepository.save(data);
  }

  @Override
  public Role update(Role data) {
    return roleRepository.save(data);
  }

  @Override
  public void delete(Integer id) {
    roleRepository.deleteById(id);
  }
}
