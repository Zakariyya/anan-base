package com.anan.springboot.authshiro.authshiro.dao;

import com.urundp.desktop.vda.auth.entity.Role;
import com.urundp.desktop.vda.auth.entity.User;
import com.urundp.desktop.vda.auth.model.UserAuthModel;
import com.urundp.desktop.vda.auth.model.UserInfoModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate` FROM `user`")
	List<UserInfoModel> selectAll();
	
	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate` FROM `user` limit #{page},#{size}")
	List<UserInfoModel> userListLimit(@Param("page") Integer page, @Param("size") Integer size);

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate` FROM `user`")
	List<UserInfoModel> userListLimitCount();

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate` FROM `user` WHERE `LOGIN_NAME` in( ${usernames} ) limit #{page},#{size}")
	List<UserInfoModel> userListLimitByuserNames(@Param("usernames") String usernames, @Param("page") Integer page, @Param("size") Integer size);
	
	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate` FROM `user` WHERE `LOGIN_NAME` in( ${usernames} ) ")
	List<UserInfoModel> userListLimitByuserNamesCount(@Param("usernames") String usernames);
	

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate`, `ROLE` FROM `user`  WHERE `ID` = #{id}")
	UserInfoModel selectById(@Param("id") Integer id);

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `PASSWORD`, "
			+ "`BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, `ICON`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate`,`STATE`  FROM `user` WHERE `LOGIN_NAME` = #{loginName}")
	User selectByLoginName(@Param("loginName") String loginName);

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `PASSWORD`, `SALT`, `ROLE` FROM `user` WHERE `LOGIN_NAME` = #{loginName}")
	UserAuthModel selectUserAuthModelByLoginName(@Param("loginName") String loginName);

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `PASSWORD`, `SALT`, `ROLE` FROM `user` WHERE `ID` = #{id}")
	UserAuthModel selectUserAuthModelById(@Param("id") Integer id);

	@Options(useGeneratedKeys = true)
	@Insert("INSERT INTO `user`(`LOGIN_NAME`, `NAME`,`PASSWORD`, `SALT`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` , `PREVIOUS_VISIT` , `CREATE_DATE`, `ROLE`) "
			+ "VALUE(#{loginName},#{name},#{password},#{salt},#{birthday},#{gender},#{email},#{phone},"
			+ "#{loginCount},#{previousVisit},#{createDate},#{role}) ")
	int insertNewUser(User user);

	@Update("UPDATE `user` SET `PASSWORD`=#{password}, `SALT`=#{salt} WHERE `ID`=#{id}")
	int updatePassword(UserAuthModel user);

	@Delete("DELETE FROM `user` WHERE `id` = #{id}")
	public int delete(@Param("id") Integer id);

	@Update("UPDATE `user` SET `NAME`=#{name}, `BIRTHDAY`=#{birthday}, `GENDER`=#{gender}, `EMAIL`=#{email}, `PHONE`=#{phone} WHERE `ID`=#{id}")
	int updateProperty(User user);

	@Update("UPDATE `user` SET `ROLE`=#{role} WHERE `ID`=#{id}")
	int updateRole(@Param("id") Integer userId, @Param("role") Role role);

	@Select("SELECT `ID`, `LOGIN_NAME` `loginName`, `NAME`, `BIRTHDAY` , `GENDER`, `EMAIL`, `PHONE`, "
			+ "`LOGIN_COUNT` `loginCount`, `PREVIOUS_VISIT` `previousVisit`, "
			+ "`CREATE_DATE` `createDate`, `ROLE` FROM `user`  WHERE `LOGIN_NAME` = #{loginname}")
	public UserInfoModel selectUserInfoByLoginName(@Param("loginname") String loginname);

	/**
	 * 获取上一语句影响的行数
	 * @return
	 */
	@Select("select FOUND_ROWS() ")
	public int queryRows();

}