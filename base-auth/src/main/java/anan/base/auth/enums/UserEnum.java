package anan.base.auth.enums;

import lombok.Getter;

@Getter
public enum UserEnum {

  /**
   * sex
   */
  MAN(0, "man"),
  WOMEN(1,"women"),


  ;

  private Integer code;
  private String message;

  UserEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}

