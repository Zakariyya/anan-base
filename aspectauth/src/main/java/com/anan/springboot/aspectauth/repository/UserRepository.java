package com.anan.springboot.aspectauth.repository;

import com.anan.springboot.aspectauth.orm.Role;
import com.anan.springboot.aspectauth.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author anan
 * Created by anan on 2018/8/27.
 */
public interface UserRepository  extends JpaRepository<User, Integer>, JpaSpecificationExecutor<Role> {

  User findAllByAccountAndPassword(User data);

}
