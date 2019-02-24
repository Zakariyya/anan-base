package anan.base.rbac.enums;

import anan.base.core.enums.CodeEnum;
import lombok.Getter;

/**
 * @author anan
 * Created by anan on 2019-02-21.
 */
@Getter
public enum RbacEnum implements CodeEnum {

  /**
   * menu isShow
   */
  SHOW(0, "show"),
  HIDDEN(1,"hidden"),

  /**
   * user sex
   */
  MAN(0, "man"),
  WOMAN(1,"woman"),


  NOT_FOUND(1100,"the user could not be found")

  ;

  private Integer code;
  private String message;

  RbacEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}

