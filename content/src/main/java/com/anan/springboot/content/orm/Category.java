package com.anan.springboot.content.orm;

import com.anan.springboot.core.orm.DictOption;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yaokunyi
 * Created on 2018/8/23.
 */
@Entity
@Data
@DynamicUpdate
public class Category implements Serializable {

  private static final long serialVersionUID = 1912706247667523727L;


  private Integer id;

  private Category parent;

  private DictOption categoryType;

  private String title;

  private String remark;

  private Date createTime;

  private Date updateTime;








}
