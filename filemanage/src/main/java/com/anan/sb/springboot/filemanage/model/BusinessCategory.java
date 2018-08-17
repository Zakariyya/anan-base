package com.urundp.corona.file.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

/**
 * 业务类别
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@Table(name = "business_category")
@Entity
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusinessCategory implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  /**
   * 业务类别名
   */
  @Column(name="name")
  @NotEmpty(message = "名称必填")
  private String name;

  @Column(name="create_time")
  private Timestamp createTime;

  /**
   * 描述
   */
  @Column(name="description")
  private String description;


  public Long getCreateTime() {
    return createTime.getTime();
  }

  @Override
  public String toString() {
    return "BusinessCategory{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", createTime=" + createTime.getTime() +
            ", description='" + description + '\'' +
            '}';
  }
}
