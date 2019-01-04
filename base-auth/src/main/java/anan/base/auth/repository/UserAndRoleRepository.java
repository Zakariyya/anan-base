package anan.base.auth.repository;

import anan.base.auth.orm.UserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anan
 * Created by anan on 2018/12/26.
 */
public interface UserAndRoleRepository extends JpaRepository<UserAndRole, Integer> {

}
