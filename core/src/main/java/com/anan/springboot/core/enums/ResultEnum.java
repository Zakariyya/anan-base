package com.anan.springboot.core.enums;

import lombok.Getter;

/**
 * @author yaokunyi
 * Created on 2018/8/23.
 */
@Getter
public enum ResultEnum {

  SUCCESS(0, "成功"),
  FAILURE(-1,"失败"),


  PARAM_ERROR(1, "参数不正确"),


  DELETE_SECTION(15, "部分删除失败"),




  ;

  private Integer code;
  private String message;

  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
