package anan.base.auth.service.impl;

import anan.base.auth.service.UserService;
import anan.base.auth.orm.User;
import anan.base.core.orm.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author anan
 * @created by anan on 2018/12/27 15:05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceImplTest {

  @Autowired
  private UserService userService;

  public User makeUser(){
    val user = new User();
    user.setPassword("123456");
    user.setAccount("aa");
    user.setEmail("email");
    user.setName("name");
    user.setPhone("123456789");
    return user;
  }

  @Test
  public void findAll() {
    log.info("userService::"+userService.findAll());
  }

  @Test
  public void findOne() {
    val user = makeUser();
    user.setId(1);
    log.info("userService::"+userService.findOne(user.getId()));

  }

  @Test
  public void save() {
    val user = makeUser();
    log.info("userService::"+userService.save(user));

  }

  @Test
  public void update() {
    val user = makeUser();
    user.setId(2);
    user.setEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    log.info("userService::"+userService.update(user));

  }

  @Test
  public void delete() {
    ResponseResult ru = new ResponseResult();
    log.info("userService::"+userService.delete("2,3,4",ru));

  }
}