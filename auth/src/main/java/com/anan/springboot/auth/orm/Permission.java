package com.anan.springboot.auth.orm;

import com.anan.springboot.auth.AuthTable;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yaokunyi
 * Created on 2018/8/27.
 */
@Entity(name= AuthTable.permission)
@Data
@DynamicUpdate
public class Permission {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private Long pid;

  private String name;

  private String url;

  private String remark;




}
