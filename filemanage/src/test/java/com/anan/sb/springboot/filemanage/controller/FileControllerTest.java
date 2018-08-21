package com.anan.sb.springboot.filemanage.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;


  @Before
  public void setupMockMvc(){
    mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
  }


  @Test
  public void findAll() {
  }

  @Test
  public void findOne() {
  }

  @Test
  public void save() {
  }

  @Test
  public void update() {
  }

  @Test
  public void delete() {
  }

  @Test
  public void upload() {
  }

  @Test
  public void download() {
  }
}