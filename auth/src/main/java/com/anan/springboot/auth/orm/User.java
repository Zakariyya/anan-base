package com.anan.springboot.auth.orm;

import com.anan.springboot.auth.AuthTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author yaokunyi
 * Created on 2018/8/27.
 */
@Entity(name= AuthTable.user)
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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





}
