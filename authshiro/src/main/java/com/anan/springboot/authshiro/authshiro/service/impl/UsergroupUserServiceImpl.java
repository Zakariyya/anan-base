package com.anan.springboot.authshiro.authshiro.service.impl;

import com.urundp.desktop.vda.auth.dao.UsergroupUserMapper;
import com.urundp.desktop.vda.auth.entity.UsergroupUser;
import com.urundp.desktop.vda.auth.service.UsergroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsergroupUserServiceImpl implements UsergroupUserService {

	@Autowired
	private UsergroupUserMapper usergroupUserMapper;

	@Override
	public List<String> selectUserLoginNameByGroupId(long groupId) {
		return usergroupUserMapper.selectUserByGroupId(groupId);
	}

	@Override
	public void deleteByUserLoginName(String user) {
		usergroupUserMapper.deleteByUser(user);
	}

	@Override
	public int insert(UsergroupUser usergroupUser) {
		return usergroupUserMapper.insert(usergroupUser);
	}

}
