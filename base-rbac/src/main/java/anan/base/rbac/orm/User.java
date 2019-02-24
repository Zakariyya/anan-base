package anan.base.rbac.orm;

import anan.base.rbac.RbacTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author anan
 * Created on 2019-02-21.
 */
@Entity(name= RbacTable.user)
@Data
@DynamicUpdate
public class User implements Serializable {


  private static final long serialVersionUID = -4877983161912080582L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;


  @Column(name = "account")
  @NotNull(message = "account cannot be null")
  private String account;

  @Column(name = "password")
  @NotNull(message = "password cannot be null")
  @JsonIgnore
  private String password;

  @Column(name = "name")
  @NotNull(message = "name cannot be null")
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


  @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
  @JoinTable(name = RbacTable.user_role, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  @SortNatural
  private Set<Role> roles;

//  @Column(name = "image")
//  private String image;       //user head icon path, need file upload
//
//  /**
//   * last login  ip
//   */
//  @Column(name = "last_ip")
//  private String lastIp;
//
//  /**
//   * last login time
//   */
//  @Column(name = "last_time")
//  private Timestamp lastTime;



//  @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
//  @JoinTable(name = RabcTable.userAndRole, joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//  @SortNatural
//  private Set<Role> roles;

}
