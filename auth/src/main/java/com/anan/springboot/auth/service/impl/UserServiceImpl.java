package com.anan.springboot.auth.service.impl;

import com.anan.springboot.auth.orm.User;
import com.anan.springboot.auth.repository.UserRepository;
import com.anan.springboot.auth.service.UserService;
import com.anan.springboot.core.exception.CoreException;
import com.anan.springboot.core.orm.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findOne(Integer id) {
    return userRepository.findById(id).get();
  }

  @Override
  public User save(User data) {
    return userRepository.save(data);
  }

  @Override
  public User update(User data) {
    return userRepository.save(data);
  }

  @Override
  public ResponseResult delete(String id, ResponseResult result) {

    String[] ids = id.split(",");
    //foreach delete, if failure? jump in catch add message,	and then continue
    for (String sid : ids) {
      try{
        userRepository.deleteById(Integer.parseInt(sid));
      }catch (CoreException e){
        result.setCode(ResponseResult.SUCCESS);
        result.addMessage("用户名为"+userRepository.findById(Integer.parseInt(sid)).get().getAccount()+"删除失败");
      }
    }
    return result;
  }


}
