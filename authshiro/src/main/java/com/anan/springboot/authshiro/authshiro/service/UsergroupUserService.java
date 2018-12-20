package com.anan.springboot.authshiro.authshiro.service;

import com.urundp.desktop.vda.auth.entity.UsergroupUser;

import java.util.List;

public interface UsergroupUserService {

	List<String> selectUserLoginNameByGroupId(long groupId);

	void deleteByUserLoginName(String user);

	int insert(UsergroupUser usergroupUser);

}
