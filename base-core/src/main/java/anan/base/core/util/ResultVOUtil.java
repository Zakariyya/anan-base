package anan.base.core.util;

import anan.base.core.enums.ResultEnum;
import anan.base.core.orm.ResponseResult;
import anan.base.core.vo.ResultVO;

import java.io.Serializable;

/**
 * @author anan
 * Create on 2018/8/18
 */
public class ResultVOUtil implements Serializable {

  private static final long serialVersionUID = 7251966408535541765L;
  public static ResultVO success() {
    return success(null);
  }

  public static ResultVO success(Object object) {
    ResultVO resultVO = new ResultVO();
//  Gson G = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//  Gson G = new GsonBuilder().setDateFormat(DateFormat.Long).create();
//    resultVO.setData(new Gson().toJson(object));
    resultVO.setData(object);
    resultVO.setCode(0);
    resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
    return resultVO;
  }

  public static ResultVO error(Integer code, String msg) {
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }

  public static ResultVO result(ResponseResult result) {
    if(!result.hasMessages() || null != result.getMessage())
      return success();
    return error(result.getCode(), result.getMessage());
  }

}
