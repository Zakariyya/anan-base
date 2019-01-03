package anan.base.comment.dto;

import anan.base.comment.orm.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author anan
 * Created on 2018/8/22.
 */
@Data
public class CommentDto{

  private String id;

  private Comment parent;

  @NotBlank(message = "内容必填")
  private String content;

  private Long createTime;

  private Long updateTime;

}
