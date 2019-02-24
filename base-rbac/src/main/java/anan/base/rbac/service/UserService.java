package anan.base.rbac.service;

import anan.base.core.orm.ResponseResult;
import anan.base.rbac.orm.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:38
 */
public interface UserService {


  Page<User> findAll(Pageable pageable);

  List<User> findAll();

  User findOne(Integer id);

  User save(User data);

  User update(User data) ;

  ResponseResult delete(String id, ResponseResult result);


}
