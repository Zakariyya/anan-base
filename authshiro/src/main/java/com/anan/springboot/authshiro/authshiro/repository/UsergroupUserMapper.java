package com.anan.springboot.authshiro.authshiro.repository;


import java.util.List;

public interface UsergroupUserMapper {

	@Select("SELECT `usergroup_id` `usergroupId`,`user` FROM `usergroup_user`")
	public List<UsergroupUser> selectAll();

	@Select("SELECT `user` FROM `usergroup_user` WHERE `usergroup_id`=#{groupId}")
	public List<String> selectUserByGroupId(@Param("groupId") long groupId);

	@Insert("INSERT INTO `usergroup_user`(`usergroup_id`,`user`) VALUE(#{usergroupId},#{user})")
	public int insert(UsergroupUser usergroupUser);

	@Delete("DELETE FROM `usergroup_user` WHERE `usergroup_id`=#{usergroupId} AND `user`=#{user}")
	public void delete(UsergroupUser usergroupUser);

	@Delete("DELETE FROM `usergroup_user` WHERE `usergroup_id`=#{usergroupId}")
	public void deleteByUsergroupId(@Param("usergroupId") long usergroupId);

	@Delete("DELETE FROM `usergroup_user` WHERE `user`=#{user}")
	public void deleteByUser(@Param("user") String user);

}
