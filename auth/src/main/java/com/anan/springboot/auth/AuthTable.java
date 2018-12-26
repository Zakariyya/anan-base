package com.anan.springboot.auth;

/**
 * @author anan
 * Created by anan on 2018/8/22.
 */
public interface AuthTable {

  String user = "auth_user";

  String role = "auth_role";

  String permission = "auth_permission";

  /*中间表*/
  String userAndRole = "auth_user_role";


}
