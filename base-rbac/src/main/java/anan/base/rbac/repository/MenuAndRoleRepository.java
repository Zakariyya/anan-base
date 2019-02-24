package anan.base.rbac.repository;

import anan.base.rbac.orm.MenuAndRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:34
 */
public interface MenuAndRoleRepository extends JpaRepository<MenuAndRole, Integer> {//, JpaSpecificationExecutor<Role> {

  List<MenuAndRole> findAllByMenuId(Integer menuId);

  List<MenuAndRole> findAllByRoleId(Integer id);

}
