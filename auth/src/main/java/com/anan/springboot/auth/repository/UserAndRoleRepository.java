package com.anan.springboot.auth.repository;

import com.anan.springboot.auth.orm.UserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anan
 * Created by anan on 2018/12/26.
 */
public interface UserAndRoleRepository extends JpaRepository<UserAndRole, Integer> {

}
