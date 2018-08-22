package com.anan.springboot.comment.service.impl;

import com.anan.springboot.comment.orm.Comment;
import com.anan.springboot.core.orm.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CommentServiceImplTest {

  @Autowired
  private CommentServiceImpl commentService;


  public Comment make(){
    Comment data = new Comment();
    data.setId(UUID.randomUUID().toString());
//    data.setParentId();
    data.setContent("this is a comment by test");
    data.setCreateTime(new Date());
    data.setUpdateTime(new Date());
    return data;
  }


  @Test
  public void findAll() {
    log.info("== findAll ==="+commentService.findAll());
  }

  @Test
  public void findOne() {

    log.info("== findOne ==="+commentService.findOne(""));

  }

  @Test
  public void save() {
    Comment make = make();
    log.info("save ==== make:::"+make);
    log.info("=== save ===="+commentService.save(make));
  }

  @Test
  public void update() {
    Comment one = commentService.findOne("");
    one.setContent("this is a modify test");
    log.info("==== update ==="+commentService.save(one));
  }

  @Test
  public void delete() {
    ResponseResult result = new ResponseResult();
    log.info("==== update ==="+commentService.delete("aaaaaaaaaaaa",result));

  }
}