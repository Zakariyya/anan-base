package com.anan.springboot.authshiro.authshiro.service;

import com.anan.springboot.authshiro.authshiro.orm.UsergroupUser;

import java.util.List;

public interface UsergroupUserService {

	List<String> selectUserLoginNameByGroupId(long groupId);

	void deleteByUserLoginName(String user);

	int insert(UsergroupUser usergroupUser);

}
