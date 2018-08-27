package com.anan.springboot.auth.repository;

import com.anan.springboot.auth.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/27.
 */
public interface RoleRepository  extends JpaRepository<User, Integer> {

}
