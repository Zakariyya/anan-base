package com.anan.springboot.comment.exception;


import com.anan.springboot.core.enums.ResultEnum;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
public class CommentException extends RuntimeException{

  private Integer code;


  public CommentException(ResultEnum globalEnum) {
    super(globalEnum.getMessage());
    this.code = globalEnum.getCode();
  }

  public CommentException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
