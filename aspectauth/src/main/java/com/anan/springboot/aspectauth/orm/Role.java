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
@Entity(name= AspectAuthTable.role)
@Data
@DynamicUpdate
public class Role implements Serializable {


  private static final long serialVersionUID = 5546290019794414001L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;




}
