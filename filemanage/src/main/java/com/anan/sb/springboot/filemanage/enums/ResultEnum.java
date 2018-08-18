package com.anan.sb.springboot.filemanage.enums;

import lombok.Getter;

/**
 * @author anan
 * Create on 2018/8/19
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    //FAILURE()?? 他娘的，成功一个状态就够了，失败那么多个，标记个鬼啊。返回下列枚举


    PARAM_ERROR(1, "参数不正确"),


    FILE_DELETE_SECTION(15, "部分删除失败"),


    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),
    LOGOUT_SUCCESS(26, "登出成功"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
