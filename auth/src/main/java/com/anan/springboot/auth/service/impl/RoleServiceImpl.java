package com.anan.springboot.auth.service.impl;

import com.anan.springboot.auth.orm.Role;
import com.anan.springboot.auth.repository.RoleRepository;
import com.anan.springboot.auth.service.RoleService;
import com.anan.springboot.core.orm.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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
