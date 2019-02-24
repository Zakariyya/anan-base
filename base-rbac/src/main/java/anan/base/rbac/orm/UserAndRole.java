package anan.base.rbac.orm;

import anan.base.rbac.RbacTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author anan
 * @created by anan on 2019/2/22 10:13
 */
@Entity(name= RbacTable.user_role)
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserAndRole {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @JoinColumn(name="user_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private User user;

  @JoinColumn(name="role_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private Role role;

}
