package com.anan.springboot.auth.controller;

import com.anan.springboot.auth.orm.User;
import com.anan.springboot.auth.service.UserService;
import com.anan.springboot.core.enums.ResultEnum;
import com.anan.springboot.core.exception.CoreException;
import com.anan.springboot.core.util.ResultVOUtil;
import com.anan.springboot.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/27.
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
   * save
   * @param data :UserDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultVO save(@Valid @RequestBody User data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【权限管理-用户】参数不正确, User={}", data);
      throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    userService.save(data);
    return ResultVOUtil.success();
  }


}
