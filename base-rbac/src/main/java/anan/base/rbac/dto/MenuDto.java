package anan.base.rbac.dto;

import lombok.Data;

/**
 * @author anan
 * @created by anan on 2019/2/22 13:25
 */
@Data
public class MenuDto {

  private Integer id;

  private Integer parentId;

  private String moduleId;

  private String moduleName;

  private String name;

  private String menuType;

  private String menuUrl;

  private String isLeaf;

  private String icon;


  /**
   * 0:show 1：hide
   * use package org.urundp.corona.pub.enums.PubEnum
   */
  private Integer isShow;

  private Integer orderNo;



}
