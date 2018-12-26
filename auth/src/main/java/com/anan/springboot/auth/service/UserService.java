package com.anan.springboot.auth.service;

import com.anan.springboot.auth.orm.User;
import com.anan.springboot.core.orm.ResponseResult;

import java.util.List;

/**
 * @author anan
 * Created by anan on 2018/8/27.
 */
public interface UserService {

  List<User> findAll();

  User findOne(Integer id);

  User save(User data);

  User update(User data);

  ResponseResult delete(String id, ResponseResult result);





}
