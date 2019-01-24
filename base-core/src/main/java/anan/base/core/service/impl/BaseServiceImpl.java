package anan.base.core.service.impl;

import anan.base.core.enums.ResultEnum;
import anan.base.core.exception.CoreException;
import anan.base.core.orm.ResponseResult;
import anan.base.core.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author anan
 * @created 2019/1/23 11:50
 */

@Service
public class BaseServiceImpl implements BaseService {


  @Override
  public ResponseResult delete(String id, JpaRepository t, ResponseResult result) {
    String[] ids = id.split(",");
    //foreach delete, if failure? jump in catch add message,	and then continue
    for (String sid : ids) {
      try {
        if (!t.existsById(Integer.parseInt(sid)))
          throw new CoreException(ResultEnum.DELETE_SECTION);
        t.deleteById(Integer.parseInt(sid));
      } catch (CoreException e) {
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
      }
    }
    return result;
  }
}
