package com.anan.springboot.authshiro.authshiro.service.impl;

import com.urundp.desktop.vda.auth.dao.UsergroupMapper;
import com.urundp.desktop.vda.auth.entity.Usergroup;
import com.urundp.desktop.vda.auth.service.UsergroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UsergroupServiceImpl implements UsergroupService {

	@Autowired
	private UsergroupMapper usergroupMapper;

	@Override
	public List<Usergroup> selectAll() {
		return usergroupMapper.selectAll();
	}

	@Override
	public int update(Usergroup usergroup) {
		return usergroupMapper.update(usergroup);
	}

	@Override
	public int insert(Usergroup usergroup) {
		return usergroupMapper.insert(usergroup);
	}

	@Override
	public Set<Long> selectIdByPid(long groupId) {
		return usergroupMapper.selectIdByPid(groupId);
	}

	@Override
	public void deleteById(long groupId) {
		usergroupMapper.deleteById(groupId);
	}

	@Override
	public List<Usergroup> listUsergroupByUserLoginName(String userLoginName) {
		return usergroupMapper.listUsergroupByUser(userLoginName);
	}

	@Override
	public List<Long> selectIdByName(String[] split) {
		return usergroupMapper.selectIdByName(split);
	}

}
