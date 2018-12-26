package com.anan.springboot.aspectauth.repository;

import com.anan.springboot.aspectauth.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anan
 * Created by anan on 2018/8/27.
 */
public interface RoleRepository  extends JpaRepository<User, Integer> {

}
