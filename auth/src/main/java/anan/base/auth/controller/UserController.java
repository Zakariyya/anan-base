package anan.base.auth.controller;

import anan.base.auth.service.UserService;
import anan.base.auth.orm.User;
import anan.base.core.enums.ResultEnum;
import anan.base.core.exception.CoreException;
import anan.base.core.orm.ResponseResult;
import anan.base.core.util.ResultVOUtil;
import anan.base.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author anan
 * Created on 2018/8/27.
 * Modify on 2018/12/26.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * findAll
   * @return ResultVO<User2UserDto></>
   */
  @GetMapping("")
  public ResultVO findAll(){
    List<User> all = userService.findAll();
    return ResultVOUtil.success(all);
  }

  /**
   * findOne
   * @param id primary key
   * @return ResultVO
   */
  @GetMapping("/{id}")
  public ResultVO findOne(@PathVariable("id") Integer id){
    return ResultVOUtil.success(userService.findOne(id));
  }


  /**
   * add
   * @param data :UserDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping("")
  public ResultVO add(@Valid @RequestBody User data, BindingResult bindingResult){
    data.setId(null);
    return save(data, bindingResult);
  }


  /**
   * update & save
   * @param data :UserDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PutMapping("")
  public ResultVO save(@Valid @RequestBody User data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【权限管理-用户】参数不正确, User={}", data);
      throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    data.setPassword(encoder.encode(data.getPassword()));
    userService.save(data);
    return ResultVOUtil.success();
  }

  /**
   * delete
   * @param id :User primary key
   * @return ResultVO
   */
  @DeleteMapping("/{id}")
//  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public ResultVO delete(@PathVariable("id") String id){
    ResponseResult responseResult = new ResponseResult();
    return ResultVOUtil.result(userService.delete(id, responseResult));
  }



}
