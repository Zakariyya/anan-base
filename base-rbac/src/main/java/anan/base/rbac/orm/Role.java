package anan.base.rbac.orm;

import anan.base.rbac.RbacTable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author anan
 * @created by anan on 2019/2/22 10:18
 */

@Entity(name= RbacTable.role)
@Data
@DynamicUpdate
@ToString
public class Role implements Comparable<Role>{

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "role_name")
  @NotNull(message = "角色名称必填")
  private String roleName;

  public Role(Integer id ){
    this.id = id;
  }

  public Role(){

  }

  public Role(Integer id, String roleName) {
    this.id = id;
    this.roleName = roleName;
  }

  @Override
  public int compareTo(Role o) {
    return Integer.compare(this.id, o.id);
  }

  @ManyToMany(fetch = FetchType.EAGER, targetEntity = Menu.class)
  @JoinTable(name = RbacTable.menu_role, joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
  @SortNatural
  private Set<Menu> menus;

}
