package com.anan.springboot.core.util;

import com.anan.springboot.core.form.CoreForm;
import com.anan.springboot.core.orm.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author anan
 * Created on 2018/8/24.
 */
@Component
public class VerifyForm<T> {


  public void dataIsNullOrFormParentIdEqId(T data, CoreForm form, ResponseResult result){
    if (null == data) {
      result.addError("更新失败，无该数据");
    }else if(form.getParentId() == form.getId()){
      result.addError("更新失败，你想多了，自己不能做自己的爸爸");
    }
  }


}
