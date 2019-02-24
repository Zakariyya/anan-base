package anan.base.rbac.repository;

import anan.base.rbac.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:34
 */
public interface UserRepository extends JpaRepository<User, Integer> {//, JpaSpecificationExecutor<Role> {


  User findByAccount(String account);

}
