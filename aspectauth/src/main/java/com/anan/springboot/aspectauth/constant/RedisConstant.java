package com.anan.springboot.aspectauth.constant;

/**
 * @author anan
 * Created on 2018/8/30.
 */
public interface RedisConstant {

  String TOKEN_PREFIX = "token_%s";

  Integer EXPIRE = 7200; //2小时
}

