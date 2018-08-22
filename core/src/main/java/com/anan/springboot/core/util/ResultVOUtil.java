package com.anan.springboot.core.util;

import com.anan.springboot.core.vo.ResultVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * @author anan
 * Create on 2018/8/18
 */
public class ResultVOUtil implements Serializable {


  private static final long serialVersionUID = 7251966408535541765L;

  public static ResultVO success(Object object) {
    ResultVO resultVO = new ResultVO();
//  Gson G = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//  Gson G = new GsonBuilder().setDateFormat(DateFormat.Long).create();
//    resultVO.setData(new Gson().toJson(object));
    resultVO.setData(object);
    resultVO.setCode(0);
    resultVO.setMsg("成功");
    return resultVO;
  }

  public static ResultVO success() {
    return success(null);
  }

  public static ResultVO error(Integer code, String msg) {
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }
}
