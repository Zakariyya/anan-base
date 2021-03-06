package anan.base.core.exception;


import anan.base.core.enums.ResultEnum;
import lombok.Data;

/**
 * @author anan
 * Created on 2018/8/22.
 */
@Data
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
