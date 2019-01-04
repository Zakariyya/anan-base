package anan.base.auth.controller;

import anan.base.auth.orm.User;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * @author anan
 * @created by anan on 2018/12/28 15:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;
  ResultMatcher ok = MockMvcResultMatchers.status().isOk();

  public User makeUser(){
    val user = new User();
    user.setPassword("123456");
    user.setAccount("aa");
    user.setEmail("email");
    user.setName("name");
    user.setPhone("123456789");
    return user;
  }

  @Before
  public void setupMockMvc(){
    mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
  }

  @Test
  public void findAll() throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user");
    this.mvc.perform(builder)
            .andExpect(ok);
  }

  @Test
  public void findOne() throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user");
    this.mvc.perform(builder)
            .andExpect(ok);
  }

  @Test
  public void add() throws Exception{
    User user = makeUser();
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user");
//    this.mvc.perform(builder
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(JSON.toJSONString(user)))
//            .andExpect(ok);
    for(int i=0;i<10;i++){
      user.setAccount("aaa"+i);
      this.mvc.perform(builder
              .contentType(MediaType.APPLICATION_JSON)
              .content(JSON.toJSONString(user)))
              .andExpect(ok);
    }
  }

  @Test
  public void save() throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/user");
    User user = makeUser();
    user.setId(3);
    MvcResult mvcResult = mvc.perform(
            builder
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSON.toJSONString(user)))
            .andExpect(ok)
            .andReturn();

  }

  @Test
  public void delete() throws Exception{
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/user");
//    ResultMatcher id = MockMvcResultMatchers.model().attribute("id", "15,10,5");
    String id = "15,3,10";
    this.mvc.perform(builder
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSON.toJSONString(id)))
            .andExpect(ok)
            .andReturn();
  }
}