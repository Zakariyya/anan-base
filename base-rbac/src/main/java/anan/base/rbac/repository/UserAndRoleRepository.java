package anan.base.rbac.repository;

import anan.base.rbac.orm.UserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:34
 */
public interface UserAndRoleRepository extends JpaRepository<UserAndRole, Integer> {//, JpaSpecificationExecutor<Role> {

  List<UserAndRole> findAllByUserId(Integer userId);

  List<UserAndRole> findAllByRoleId(Integer id);

}
