package com.anan.sb.springboot.filemanage.exception;

import com.anan.sb.springboot.filemanage.enums.ResultEnum;

/**
 * 文件衣厂
 * @author anan
 * Created on 2018/8/8.
 */
public class FileException extends RuntimeException {

  private Integer code;


  public FileException(ResultEnum globalEnum) {
    super(globalEnum.getMessage());
    this.code = globalEnum.getCode();
  }

  public FileException(Integer code, String message) {
    super(message);
    this.code = code;
  }

}
