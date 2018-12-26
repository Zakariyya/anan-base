package com.anan.springboot.auth.repository;

import com.anan.springboot.auth.orm.Role;
import com.anan.springboot.auth.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author anan
 * Created by anan on 2018/8/27.
 */
public interface UserRepository  extends JpaRepository<User, Integer>, JpaSpecificationExecutor<Role> {

}
