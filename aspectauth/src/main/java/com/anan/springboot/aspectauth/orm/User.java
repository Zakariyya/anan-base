package com.anan.springboot.aspectauth.orm;

import com.anan.springboot.aspectauth.AspectAuthTable;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@Entity(name= AspectAuthTable.user)
@Data
@DynamicUpdate
public class User implements Serializable {


  private static final long serialVersionUID = 1252788352173009872L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "account")
  private String account;

  @Column(name = "password")
  private String password;

//  @Column(name = "name")
//  private String name;
//
//  @Column(name = "sex")
//  private Integer sex;
//
//  @Column(name = "email")
//  private String email;
//
//  @Column(name = "phone")
//  private String phone;


  public User(String account, String password) {
    this.account = account;
    this.password = password;
  }

  public User() {
  }

  public User(Integer id) {
    this.id = id;
  }
}
