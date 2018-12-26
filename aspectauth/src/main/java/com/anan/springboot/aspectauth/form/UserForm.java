package com.anan.springboot.aspectauth.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author anan
 * Created on 2018/8/30.
 */
@Data
public class UserForm {

  @NotNull(message = "账号不能为空")
  private String account;

  @NotNull(message = "账号不能为空")
  private String password;




}
