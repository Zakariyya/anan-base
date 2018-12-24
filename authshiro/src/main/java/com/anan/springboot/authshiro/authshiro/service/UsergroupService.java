package com.anan.springboot.authshiro.authshiro.service;


import com.anan.springboot.authshiro.authshiro.orm.Usergroup;

import java.util.List;
import java.util.Set;

public interface UsergroupService {

	List<Usergroup> selectAll();

	int update(Usergroup usergroup);

	int insert(Usergroup usergroup);

	Set<Long> selectIdByPid(long groupId);

	void deleteById(long groupId);

	List<Usergroup> listUsergroupByUserLoginName(String userLoginName);

	List<Long> selectIdByName(String[] split);

}
