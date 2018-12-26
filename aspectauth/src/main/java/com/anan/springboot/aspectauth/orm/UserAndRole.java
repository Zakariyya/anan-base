package com.anan.springboot.aspectauth.orm;

import com.anan.springboot.aspectauth.AspectAuthTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author anan
 * Created on 2018/8/27.
 */
@Entity(name= AspectAuthTable.userAndRole)
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserAndRole implements Serializable {

  private static final long serialVersionUID = -3987892594486116271L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;

  @JoinColumn(name="user_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private User user;

  @JoinColumn(name="role_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private Role role;

}
