package com.urundp.corona.file.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * 目录
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@Table(name="h_directory")
@Entity
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HDirectory implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  /**
   * 目录名称
   */
  @Column(name="name")
  @NotEmpty(message = "名称必填")
  private String name;

  /**
   * 关联业务
   * 用枚举
   */
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "category_id")
  private BusinessCategory businessCategory;

  /**
   * 父级
   */
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "parent_id")
  private HDirectory parent;

  /**
   * 文件数量(保留，数量不多实时统计)
   */
  @Column(name="quantity")
  private Integer quantity  ;

  public HDirectory(Integer id) {
    this.id = id;
  }

  public HDirectory() {
  }


}
