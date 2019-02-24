package anan.base.rbac.controller;

import anan.base.core.enums.ResultEnum;
import anan.base.core.orm.ResponseResult;
import anan.base.core.util.ResultVOUtil;
import anan.base.core.vo.ResultVO;
import anan.base.rbac.converter.Menu2MenuDto;
import anan.base.rbac.dto.MenuDto;
import anan.base.rbac.orm.Menu;
import anan.base.rbac.service.MenuAndRoleService;
import anan.base.rbac.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * 菜单配置
 * 
 * @craete by double  2018年3月6日
 * @author anan
 * @update 2019-02-21
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

	@Autowired
  MenuService menuService;

  @Autowired
  MenuAndRoleService menuAndRoleService;

	@Autowired
  Menu2MenuDto m2m;

	/**
	 * findAll by page
	 *
	 * @return ResultVO<Menu>
	 */
	@GetMapping("/{page}/{size}")
	public ResultVO findAll(@PathVariable(value = "page") Integer page,
                          @PathVariable(value = "size") Integer size) throws InstantiationException, IllegalAccessException {

		PageRequest request = new PageRequest(page - 1, size);
		Page<Menu> all = menuService.findAll(request);
		List<MenuDto> convert = m2m.convert(all.getContent(), MenuDto.class);
		return ResultVOUtil.success(new PageImpl<>(convert, request, all.getTotalElements()));
	}

	/**
	 * findAll
	 */
	@GetMapping("")
	public ResultVO findAll() throws InstantiationException, IllegalAccessException {
		val all = menuService.findAll();
		return ResultVOUtil.success(m2m.convert(all, MenuDto.class));
	}

	/**
	 * findOne
	 *
	 * @param id primary key
	 * @return ResultVO
	 */
	@GetMapping("/{id}")
	public ResultVO findOne(@PathVariable("id") Integer id) {
		return ResultVOUtil.success(menuService.findOne(id));
	}


	/**
	 * add
	 *
	 * @param data :Menu pojo
	 * @return ResultVO
	 */
	@ResponseBody
	@PostMapping("")
	public ResultVO add(@Valid @RequestBody Menu data, BindingResult bindingResult) {
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
	public ResultVO save(@PathVariable("id") Integer id, @Valid @RequestBody Menu data, BindingResult bindingResult) {

		data.setId(id);
		if (bindingResult.hasErrors()) {
			log.error("[pub 模块-菜单配置]保存参数不正确, Menu={}", data);
			return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
		}
//		val user = (User) authenticationFacade.getAuthentication().getPrincipal();
		val update = menuService.update(data);
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
		return ResultVOUtil.result(menuService.delete(id, new ResponseResult()));
	}

}