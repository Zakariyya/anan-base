package com.anan.springboot.authshiro.authshiro.orm;


import com.anan.springboot.authshiro.authshiro.AuthShiroTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = AuthShiroTable.user)
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="login_name")
	private String loginName;

	@Column(name="login_name")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="salt")
	private String salt;

	@Column(name="birthday")
	private String birthday;

	@Column(name="gender")
	private Short gender;

	@Column(name="email")
	private String email;

	@Column(name="phone")
	private String phone;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="update_time")
	private Date updateTime;

	/**
	 * 登录次数
	 */
	@Column(name="login_count")
	private Integer loginCount;

	/**
	 * 上次访问时间
	 */
	@Column(name="previous_visit")
	private String previousVisit;

	/**
	 * 最后登录时间
	 */
	@Column(name="lastVisit")
	private String lastVisit;

	@JoinColumn(name="content_category_type")
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
	private Role role;



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}