package anan.base.rbac.orm;

import anan.base.rbac.RbacTable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 菜单配置
 * @craete by double  2018年3月6日
 * @author anan
 * @update 2019-02-21
 */
@Entity(name= RbacTable.menu)
@Data
@DynamicUpdate
public class Menu implements Serializable,Comparable<Menu>{

  private static final long serialVersionUID = -5582275451037307672L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @JoinColumn(name="parent_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
  private Menu parentId;

  @Column(name = "module_id")
  private String moduleId;

  @Column(name = "module_name")
  private String moduleName;

  @Column(name = "name")
  private String name;

  /**
   * 为后面权限控制到“按钮”级别，做预留
   * 这里数据库是用varchar，为避免影响现有的前端代码，不修改类型
   *
   * 如果要调用的话，用枚举。
   * 用：BUTTON，MENU 来标识
   */
  @Column(name = "menu_type")
  private String menuType;

  @Column(name = "menu_url")
  private String menuUrl;


  @Column(name = "icon")
  private String icon;

  /**
   * 0:show 1：hide
   * use package org.urundp.corona.pub.enums.PubEnum
   */
  @Column(name = "is_show")
  private Integer isShow;

  @Column(name = "order_no")
  private Integer orderNo;


  @Override
  public int compareTo(Menu o) {
    return  Integer.compare(this.id, o.id);
  }
}
