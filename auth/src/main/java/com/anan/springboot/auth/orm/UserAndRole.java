package com.anan.springboot.auth.orm;

import com.anan.springboot.auth.AuthTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author yaokunyi
 * Created on 2018/8/27.
 */
@Entity(name= AuthTable.userAndRole)
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserAndRole {

  @JoinColumn(name="user_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private User user;

  @JoinColumn(name="role_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private Role role;

}
