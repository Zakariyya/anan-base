package com.anan.springboot.auth.orm;

import com.anan.springboot.auth.AuthTable;
import com.anan.springboot.auth.enums.UserEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@Entity(name= AuthTable.user)
@Data
@DynamicUpdate
public class User implements Serializable {

  private static final long serialVersionUID = 6650020328552169838L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "account")
  private String account;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  /**
   * in UserEnum
   * package com.anan.springboot.auth.enums;
   */
  @Column(name = "sex")
  private Integer sex;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;


//  @Column(name = "image")
//  private String image;       //用户头像

  /**
   * last login  ip
   */
  @Column(name = "last_ip")
  private String lastIp;

  /**
   * last login time
   */
  @Column(name = "last_time")
  private Date lastTime;


  @ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class)
  @JoinTable(name = AuthTable.userAndRole, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  @SortNatural
  private Set<Role> roles;


}
