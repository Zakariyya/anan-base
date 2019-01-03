package com.anan.springboot.aspectauth.service;

import com.anan.springboot.aspectauth.orm.User;
import anan.base.core.orm.ResponseResult;

import java.util.List;

/**
 * @author anan
 * Created by anan on 2018/8/27.
 */
public interface UserService {

  List<User> findAll();

  User findOne(Integer id);

  User findOneByAccountAndPassword(String account, String password);

  User save(User data);

  User update(User data);

  void delete(String id, ResponseResult result);





}
