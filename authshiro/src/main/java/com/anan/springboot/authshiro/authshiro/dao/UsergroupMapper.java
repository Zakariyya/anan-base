package com.anan.springboot.authshiro.authshiro.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户组
 * 
 * @author anan
 * @date Created by anan on 2018/12/20
 *
 */
public interface UsergroupMapper {

	@Select("SELECT `id`,`name`,`p_id` `pId` FROM `usergroup` ORDER BY `id` ASC")
	public List<Usergroup> selectAll();

	@Insert("INSERT INTO `usergroup`(`name`,`p_id`) VALUE(#{name},#{pId})")
	public int insert(Usergroup userGroup);

	@Insert("UPDATE `usergroup` SET `name`=#{name} WHERE `id`=#{id}")
	public int update(Usergroup userGroup);

	@Delete("DELETE FROM `usergroup` WHERE `id`=#{id}")
	public void deleteById(@Param("id") long id);

	@Select("SELECT `id` FROM `usergroup`  WHERE `p_id`=#{pId}")
	public Set<Long> selectIdByPid(@Param("pId") long pId);

	@Select("SELECT `id`,`name`,`p_id` `pId` FROM `usergroup` WHERE "
			+ "`id` IN (SELECT `usergroup_id` `usergroupId` FROM `usergroup_user` WHERE `user`=#{user}) "
			+ "ORDER BY `id` ASC")
	public List<Usergroup> listUsergroupByUser(@Param("user") String user);

	@SelectProvider(type = TutorDynaSqlProvider.class, method = "selectIdByName")
	public List<Long> selectIdByName(@Param("name") String[] name);

	static class TutorDynaSqlProvider {
		public String selectIdByName(Map<String, Object> map) {
			String[] name = (String[]) map.get("name");
			StringBuffer sb = new StringBuffer("`name`in (");
			for (String n : name) {
				if ("".equals(n))
					continue;
				sb.append("'").append(n).append("'").append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
			return new SQL() {
				{
					SELECT("id");
					FROM("usergroup");
					WHERE(sb.toString());
				}
			}.toString();
		}
	}

}
