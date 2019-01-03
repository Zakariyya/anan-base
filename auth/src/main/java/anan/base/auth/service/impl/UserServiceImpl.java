package anan.base.auth.service.impl;

import anan.base.auth.service.UserService;
import anan.base.auth.orm.User;
import anan.base.auth.repository.UserRepository;
import anan.base.core.enums.ResultEnum;
import anan.base.core.exception.CoreException;
import anan.base.core.orm.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@Service
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

  @Transactional
  @Override
  public User save(User data) {
    return userRepository.save(data);
  }

  @Transactional
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
        if(!userRepository.existsById(Integer.parseInt(sid)))
          throw new CoreException(ResultEnum.DELETE_SECTION);
        userRepository.deleteById(Integer.parseInt(sid));
      }catch (CoreException e){
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
      }
    }
    return result;
  }


}
