package com.anan.springboot.core.exception;


import com.anan.springboot.core.enums.ResultEnum;

/**
 * @author anan
 * Created on 2018/8/22.
 */
public class CoreException extends RuntimeException{

  private Integer code;


  public CoreException(ResultEnum globalEnum) {
    super(globalEnum.getMessage());
    this.code = globalEnum.getCode();
  }

  public CoreException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
