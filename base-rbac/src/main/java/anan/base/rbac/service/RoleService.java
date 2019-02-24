package anan.base.rbac.service;

import anan.base.core.orm.ResponseResult;
import anan.base.rbac.orm.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:38
 */
public interface RoleService {


  Page<Role> findAll(Pageable pageable);

  List<Role> findAll();

  Role findOne(Integer id);

  Role save(Role data);

  Role update(Role data) ;


  ResponseResult delete(String id, ResponseResult result);


}
