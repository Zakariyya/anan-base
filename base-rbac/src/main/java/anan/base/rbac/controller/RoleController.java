package anan.base.rbac.controller;

import anan.base.core.enums.ResultEnum;
import anan.base.core.orm.ResponseResult;
import anan.base.core.util.ResultVOUtil;
import anan.base.core.vo.ResultVO;
import anan.base.rbac.orm.Menu;
import anan.base.rbac.orm.Role;
import anan.base.rbac.orm.User;
import anan.base.rbac.security.AuthenticationFacade;
import anan.base.rbac.service.MenuAndRoleService;
import anan.base.rbac.service.RoleService;
import anan.base.rbac.service.UserAndRoleService;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 菜单配置
 * 
 * @craete by double  2018年3月6日
 * @author anan
 * @update 2019-02-21
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

	@Autowired
  RoleService roleService;
	@Autowired
	private AuthenticationFacade authenticationFacade;
  @Autowired
  MenuAndRoleService menuAndRoleService;
	@Autowired
  UserAndRoleService userAndRoleService;

	/**
	 * findAll by page
	 *
	 * @return ResultVO<SystemParameter>
	 */
	@GetMapping("/{page}/{size}")
	public ResultVO findAll(@PathVariable(value = "page") Integer page,
                          @PathVariable(value = "size") Integer size) {

		/**
		 * in springboot 2.x ,
		 * PageRequest do not recommend new PageRequest(x).
		 * become PageRequest.of(x)
		 */
		PageRequest request = new PageRequest(page - 1, size);

		Page<Role> all = roleService.findAll(request);
//		List<ProcessDto> convert = p2p.convert(all.getContent(), ProcessDto.class);
//		return ResultVOUtil.success(new PageImpl<>(convert, request, all.getTotalElements()));

		return ResultVOUtil.success(all);
	}

	/**
	 * findAll
	 */
	@GetMapping("")
	public ResultVO findAll() throws InstantiationException, IllegalAccessException {
		var all = roleService.findAll();
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
		return ResultVOUtil.success(roleService.findOne(id));
	}


	/**
	 * add
	 *
	 * @param data :ProcessDto pojo
	 * @return ResultVO
	 */
	@ResponseBody
	@PostMapping("")
	public ResultVO add(@Valid @RequestBody Role data, BindingResult bindingResult) {
		data.setId(null);
//		var user = (User) authenticationFacade.getAuthentication().getPrincipal();
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
	public ResultVO save(@PathVariable("id") Integer id, @Valid @RequestBody Role data, BindingResult bindingResult) {

		data.setId(id);
		if (bindingResult.hasErrors()) {
			log.error("[pub 模块-角色配置]保存参数不正确, Menu={}", data);
			return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
		}
//		var user = (User) authenticationFacade.getAuthentication().getPrincipal();
		var update = roleService.update(data);
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
		return ResultVOUtil.result(roleService.delete(id, new ResponseResult()));
	}

  /**
   * about role by menu
   *
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  @GetMapping("/findRoleByMenu")
  public ResultVO findRoleByMenu(){
    Menu menu = new Menu();
	  menu.setId(1);
    var all = menuAndRoleService.findRoleByMenu(menu);
    return ResultVOUtil.success(all);
  }

  @GetMapping("/findMenuByRole")
  public ResultVO findMenuByRole(){
    Role role = new Role();
    role.setId(1);
    var all = menuAndRoleService.findMenuByRole(role);
    return ResultVOUtil.success(all);
  }


	/**
	 * about role by user
	 *
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@GetMapping("/roleByUser")
	public ResultVO findRoleByUser() {
		var user = (User) authenticationFacade.getAuthentication().getPrincipal();
		var all = userAndRoleService.findRoleByUser(user);
		return ResultVOUtil.success(all);
	}

	@GetMapping("/userByRole/{roleId}")

	public ResultVO findUserByRole(@PathVariable(value = "roleId") Integer roleId){
		var all = userAndRoleService.findUserByRole(new Role(roleId));
		return ResultVOUtil.success(all);
	}

}