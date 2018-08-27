package com.anan.springboot.auth.orm;

import com.anan.springboot.auth.AuthTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yaokunyi
 * Created on 2018/8/27.
 */
@Entity(name= AuthTable.authUser)
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

  private static final long serialVersionUID = 6650020328552169838L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "role_id")
  private Role roleId;

  @Column(name = "account")
  private String account;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  @Column(name = "sex")
  private Integer sex;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

}
