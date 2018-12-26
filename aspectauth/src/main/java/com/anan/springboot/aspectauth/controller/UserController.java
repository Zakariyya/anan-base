package com.anan.springboot.aspectauth.controller;

import com.anan.springboot.aspectauth.constant.CookieConstant;
import com.anan.springboot.aspectauth.constant.RedisConstant;
import com.anan.springboot.aspectauth.form.UserForm;
import com.anan.springboot.aspectauth.orm.User;
import com.anan.springboot.aspectauth.service.UserService;
import com.anan.springboot.aspectauth.util.CookieUtil;
import com.anan.springboot.core.enums.ResultEnum;
import com.anan.springboot.core.exception.CoreException;
import com.anan.springboot.core.util.ResultVOUtil;
import com.anan.springboot.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private StringRedisTemplate redisTemplate;


  @GetMapping("/login")
  public ResultVO login(@Valid @RequestBody UserForm form, BindingResult bindingResult,
                            HttpServletResponse response,
                            Map<String, Object> map) {

    //1. openid去和数据库里的数据匹配
    val data= userService.findOneByAccountAndPassword(form.getAccount(), form.getPassword());
    if (data == null) {
      map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
      map.put("url", "/user/seller/order/list");
//      return new ModelAndView("common/error");
      return ResultVOUtil.success("common/error");
    }

    //2. 设置token至redis
    String token = UUID.randomUUID().toString();
    Integer expire = RedisConstant.EXPIRE;

    redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), form.getAccount(), expire, TimeUnit.SECONDS);

    //3. 设置token至cookie
    CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

//    return new ModelAndView("redirect:" +  "/user");
    return ResultVOUtil.success("redirect:" +  "/user");

  }

  @GetMapping("/logout")
  public ResultVO logout(HttpServletRequest request,
                             HttpServletResponse response,
                             Map<String, Object> map) {
    //1. 从cookie里查询
    Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
    if (cookie != null) {
      //2. 清除redis
      redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

      //3. 清除cookie
      CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
    }

    map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
    map.put("url", "/user/baidu/");
//    return new ModelAndView("common/success", map);
    return ResultVOUtil.success("common/success"+ map);
  }

  /**
   * baidu
   * @return baidu
   */
  @GetMapping("/baidu")
  public ResultVO aa(){
    return ResultVOUtil.success();
  }

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
