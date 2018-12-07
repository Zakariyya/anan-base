package com.anan.springboot.aspectauth;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/22.
 */
public interface AspectAuthTable {

  String user = "auth_user";

  String role = "auth_role";

  String permission = "auth_permission";

  /*中间表*/
  String userAndRole = "auth_user_role";


}
