package com.urundp.corona.file.enums;


/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/8.
 */
public enum GlobalEnum implements CodeEnum{

  SUCCESS(200,"操作成功"),
  FAILURE(500,"服务器出错"),

  FAIL(417,"操作失败"),
  PARTIALFAILED(420,"部分操作失败")
  ;

  private Integer code;
  private String message;

  GlobalEnum(Integer code, String message) {
    this.code = code;
    this.message = message;

  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
