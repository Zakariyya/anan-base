package anan.base.core.exception;

import anan.base.core.enums.ResultEnum;
import anan.base.core.util.ResultVOUtil;
import anan.base.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception Catch
 * you can google them
 * - ControllerAdvice
 * - ExceptionHandler
 *
 * @author anan
 * @created by anan on 2019/1/25 10:32
 */

@ControllerAdvice
@Slf4j
public class ExceptionCatch {

  /**
   * get the DataIntegrityViolationException and catch it
   * eg:
   * e.getCause().getCause().getMessage() ==> Duplicate entry 'anan1529' for key 'account'
   *
   * @param e
   * @return
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseBody
  public ResultVO dataIntegrityViolationException(DataIntegrityViolationException e) {
    log.error("DataIntegrityViolationException.getMessage=>{}",e.getMessage());
    log.error("DataIntegrityViolationException.getCause.getCause.getMessage=>{}",e.getCause().getCause().getMessage());

    if(e.getCause().getCause().getMessage().contains("Duplicate entry") && e.getCause().getCause().getMessage().contains("for key")){
      String[] ids = e.getCause().getCause().getMessage().split("'");
      log.error("字段 {} 为唯一索引，存在数据为 {} ，SQL无法执行，已回退", ids[3],ids[1]);
      return ResultVOUtil.error(ResultEnum.DUPLICATE_ENTRY.getCode(),ids[1]+ResultEnum.DUPLICATE_ENTRY.getMessage());
    }else {
      throw new CoreException(ResultEnum.FAILURE.getCode(), ResultEnum.FAILURE.getMessage());
    }
  }

  /**
   * get the DataIntegrityViolationException and catch it
   * eg:
   * e.getCause().getCause().getMessage() ==> Duplicate entry 'anan1529' for key 'account'
   *
   * @param e
   * @return
   */
  @ExceptionHandler(IllegalAccessException.class)
  @ResponseBody
  public ResultVO illegalAccessException(IllegalAccessException e) {
    log.error("illegalAccessException.getMessage=>{}",e.getMessage());
    log.error("illegalAccessException.getCause.getCause.getMessage=>{}",e.getCause().getCause().getMessage());
    throw new CoreException(ResultEnum.FAILURE.getCode(), ResultEnum.FAILURE.getMessage());
  }
}
