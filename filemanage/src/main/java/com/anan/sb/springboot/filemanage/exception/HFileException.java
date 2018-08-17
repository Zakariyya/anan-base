package com.urundp.corona.file.exception;


import com.urundp.corona.file.enums.GlobalEnum;

/**
 * 业务异常
 * @author yaokunyi
 * Created on 2018/8/8.
 */
public class HFileException extends RuntimeException {

  private Integer code;


  public HFileException(GlobalEnum globalEnum) {
    super(globalEnum.getMessage());
    this.code = globalEnum.getCode();
  }

  public HFileException(Integer code, String message) {
    super(message);
    this.code = code;
  }

}
