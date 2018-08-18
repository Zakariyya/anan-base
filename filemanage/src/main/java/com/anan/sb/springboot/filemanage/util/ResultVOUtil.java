package com.anan.sb.springboot.filemanage.util;

import com.anan.sb.springboot.filemanage.vo.ResultVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * @author anan
 * Create on 2018/8/18
 */
public class ResultVOUtil {
    private static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
//        Gson G = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Gson G = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    private static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
