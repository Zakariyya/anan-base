package anan.base.rbac.service.impl;

import anan.base.core.orm.ResponseResult;
import anan.base.core.service.BaseService;
import anan.base.rbac.orm.Role;
import anan.base.rbac.repository.RoleRepository;
import anan.base.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:53
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {


  @Autowired
  private RoleRepository repository;

  @Autowired
  private BaseService baseService;

  @Override
  public Page<Role> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<Role> findAll() {
    return repository.findAll();
  }

  @Override
  public Role findOne(Integer id) {
    return repository.findById(id).get();
  }

  @Transactional
  @Override
  public Role save(Role data) {
    data.setId(null);
    return this.update(data);
  }

  @Transactional
  @Override
  public Role update(Role data) {

    return repository.save(data);
  }


  @Override
  public ResponseResult delete(String id, ResponseResult result) {
    return baseService.delete(id, repository, result);
  }
}
