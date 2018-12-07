package com.anan.springboot.aspectauth.repository;

import com.anan.springboot.aspectauth.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/27.
 */
public interface RoleRepository  extends JpaRepository<User, Integer> {

}
