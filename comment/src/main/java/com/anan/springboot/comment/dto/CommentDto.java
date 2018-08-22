package com.anan.springboot.comment.dto;

import com.anan.springboot.comment.orm.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
@Data
public class CommentDto extends Comment{

  private String id;

  private Comment parent;

  @NotBlank(message = "内容必填")
  private String content;



}
