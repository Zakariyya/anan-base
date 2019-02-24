package anan.base.rbac.service.impl;

import anan.base.core.orm.ResponseResult;
import anan.base.core.service.BaseService;
import anan.base.rbac.orm.User;
import anan.base.rbac.repository.UserRepository;
import anan.base.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:53
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BaseService baseService;

  @Override
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findOne(Integer id) {
    return userRepository.findById(id).get();
  }

  @Transactional
  @Override
  public User save(User data) {
    data.setId(null);
    return this.update(data);
  }

  @Transactional
  @Override
  public User update(User data) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    data.setPassword(encoder.encode(data.getPassword()));
    return userRepository.save(data);
  }


  @Override
  public ResponseResult delete(String id, ResponseResult result) {
    return baseService.delete(id, userRepository, result);
  }
}
