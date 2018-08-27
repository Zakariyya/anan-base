package com.anan.springboot.auth.orm;

import javax.persistence.ManyToMany;

/**
 * @author yaokunyi
 * Created on 2018/8/27.
 */
public class UserAndRole {

  @ManyToMany
  private User user;


  private Role role;

}
