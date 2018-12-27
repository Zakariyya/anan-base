package com.anan.springboot.auth.orm;

import com.anan.springboot.auth.AuthTable;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@Entity(name= AuthTable.role)
@Data
@DynamicUpdate
public class Role implements Serializable {


  private static final long serialVersionUID = -2066571521414589797L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;


  @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinTable(name = AuthTable.user, joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  @SortNatural
  private Set<User> users;


}
