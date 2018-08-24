package com.anan.springboot.core.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */

public enum EnabledEnum {

  ENABLED(true, "启用"), DISABLED(false, "禁用");

  private Boolean id;

  private String name;

  public Map<String, Object> toJSONObject() {
    Map<String, Object> map = new HashMap<>();
    map.put("value", this.id);
    map.put("label", this.name);
    return map;
  }

  public static List relation() {
    ArrayList list = new ArrayList();
    list.add(EnabledEnum.ENABLED.toJSONObject());
    list.add(EnabledEnum.DISABLED.toJSONObject());
    return list;
  }

  /**
   * @param id
   * @param name
   */
  EnabledEnum(boolean id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "[id=" + this.id + ", name=" + this.name + "]";
  }

  public boolean isId() {
    return id;
  }

  public void setId(boolean id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

