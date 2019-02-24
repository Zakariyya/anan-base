package anan.base.rbac.controller;

import anan.base.core.enums.ResultEnum;
import anan.base.core.orm.ResponseResult;
import anan.base.core.util.ResultVOUtil;
import anan.base.core.vo.ResultVO;
import anan.base.rbac.orm.User;
import anan.base.rbac.service.UserAndRoleService;
import anan.base.rbac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author anan
 * @created by anan on 2019/2/21 18:16
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private UserAndRoleService userAndRoleService;

  /**
   * findAll by page
   *
   * @return ResultVO<SystemParameter>
   */
  @GetMapping("/{page}/{size}")
  public ResultVO findAll(@PathVariable(value = "page") Integer page,
                            @PathVariable(value = "size") Integer size) {

    PageRequest request = new PageRequest(page - 1, size);
    Page<User> all = userService.findAll(request);
//		List<ProcessDto> convert = p2p.convert(all.getContent(), ProcessDto.class);
//		return ResultVOUtil.success(new PageImpl<>(convert, request, all.getTotalElements()));
    return ResultVOUtil.success(all);
  }

  /**
   * findAll
   */
  @GetMapping("")
  public ResultVO findAll() throws InstantiationException, IllegalAccessException {
    val all = userService.findAll();
//		return ResultVOUtil.success(p2p.convert(all, ProcessDto.class));
    return ResultVOUtil.success(all);
  }

  /**
   * findOne
   *
   * @param id primary key
   * @return ResultVO
   */
  @GetMapping("/{id}")
  public ResultVO findOne(@PathVariable("id") Integer id) {
    return ResultVOUtil.success(userService.findOne(id));
  }


  /**
   * add
   *
   * @param data :ProcessDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping("")
  public ResultVO add(@Valid @RequestBody User data, BindingResult bindingResult) {
    data.setId(null);
//		val user = (User) authenticationFacade.getAuthentication().getPrincipal();
//		data.setCreateUserId(user);
    return save(null, data, bindingResult);
  }


  /**
   * update & save
   * <p>
   * remark:
   * throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
   * bindingResult.getFieldError().getDefaultMessage());
   *
   * @param data :ProcessDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping("/{id}")
  public ResultVO save(@PathVariable("id") Integer id, @Valid @RequestBody User data, BindingResult bindingResult) {

    data.setId(id);
    if (bindingResult.hasErrors()) {
      log.error("[pub 模块-用户配置]保存参数不正确, user={}", data);
      return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
    }
//		val user = (User) authenticationFacade.getAuthentication().getPrincipal();
    val update = userService.update(data);
    return ResultVOUtil.success(update);
  }

  /**
   * delete
   *
   * @param id :Process primary key
   * @return ResultVO
   * <p>
   * remark: @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
   */
  @DeleteMapping("/{id}")
  public ResultVO delete(@PathVariable("id") String id) {
    return ResultVOUtil.result(userService.delete(id, new ResponseResult()));
  }




}
