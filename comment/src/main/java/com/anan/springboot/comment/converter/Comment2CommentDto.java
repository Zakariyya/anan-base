package com.anan.springboot.comment.converter;

import com.anan.springboot.comment.dto.CommentDto;
import com.anan.springboot.comment.orm.Comment;
import com.anan.springboot.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
@Component
public class Comment2CommentDto {

  @Autowired
  private CommentService service;

  public CommentDto convert(Comment data) {
    CommentDto dto = new CommentDto();
    if(null != data.getParentId()){
      dto.setParent(service.findOne(data.getParentId()));
    }
    return dto;
  }


}
