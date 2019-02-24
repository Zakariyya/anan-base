package anan.base.rbac.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author anan
 * @created by anan on 2019/2/22 14:45
 */
@Data
public class MenuForm implements Serializable {

  private static final long serialVersionUID = 3688534451625388768L;

  private Integer id;

  @NotNull(message = "父级菜单ID必填，顶级为0")
  private Integer parentId;

  @NotNull(message = "模块ID必填")
  private String moduleId;

  @NotNull(message = "模块名称必填")
  private String moduleName;

  @NotNull(message = "菜单名称必填")
  private String name;

  /**
   * 为后面权限控制到“按钮”级别，做预留
   * 这里数据库是用varchar，为避免影响现有的前端代码，不修改类型
   *
   * 如果要调用的话，用枚举。
   * 用：BUTTON，MENU 来标识
   */

  private String menuType;

  @NotNull(message = "菜单路径必填")
  private String menuUrl;

  private String isLeaf;

  private String icon;

  /**
   * 0:show 1：hide
   * use package org.urundp.corona.pub.enums.PubEnum
   */
  @NotNull(message = "是否显示/隐藏 必填")
  private Integer isShow;

  @NotNull(message = "排序必填")
  private Integer orderNo;



}
