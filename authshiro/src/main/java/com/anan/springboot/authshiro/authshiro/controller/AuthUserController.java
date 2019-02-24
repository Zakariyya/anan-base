package com.anan.springboot.authshiro.authshiro.controller;

import com.anan.springboot.authshiro.authshiro.orm.Role;
import com.anan.springboot.authshiro.authshiro.orm.User;
import com.anan.springboot.authshiro.authshiro.model.PasswordModel;
import com.anan.springboot.authshiro.authshiro.model.UserInfoModel;
import com.anan.springboot.authshiro.authshiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gallardot on 16/5/31.
 */
@RestController
@RequestMapping(value = "/user")
public class AuthUserController {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<List<UserInfoModel>> findAll() {
		return new ResultVO<>(userService.select());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<UserInfoModel> find(@PathVariable("id") Integer id) {
		return new ResultVO<>(userService.select(id));
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Object> add(@RequestBody User user) {
		int count = userService.create(user);
		return new ResultVO<>().setSuccess(count > 0);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Object> delete(@PathVariable("id") Integer id) {
		int count = userService.delete(id);
		return new ResultVO<>().setSuccess(count > 0);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Object> update(@PathVariable("id") Integer id, @RequestBody User user) {
		int count = userService.updateProperty(user);
		return new ResultVO<>().setSuccess(count > 0);
	}

	@RequestMapping(value = "/{id}/role", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Role> getRole(@PathVariable("id") Integer id) {
		return new ResultVO<Role>(userService.selectRole(id));
	}

	@RequestMapping(value = "/{id}/password", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Object> updatePassword(@PathVariable("id") Integer id, @RequestBody PasswordModel passwordModel) {
		passwordModel.setUserId(id);
		int count = userService.updatePassword(passwordModel);
		return new ResultVO<>().setSuccess(count > 0);
	}

	/**
	 * 修改user的role
	 *
	 */
	@RequestMapping(value = "/{id}/role", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Object> updateRole(@PathVariable("id") Integer id, @RequestBody Role role) {
		int count = userService.updateRole(id, role);
		return new ResultVO<>().setSuccess(count > 0);
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResultVO<Object> signin(@RequestBody User user) {
		int count = userService.create(user);
		return new ResultVO<>().setSuccess(count > 0);
	}

}
