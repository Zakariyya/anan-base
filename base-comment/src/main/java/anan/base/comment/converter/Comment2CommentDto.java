package anan.base.comment.converter;

import anan.base.comment.service.CommentService;
import anan.base.comment.dto.CommentDto;
import anan.base.comment.orm.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author anan
 * Created on 2018/8/22.
 *
 * note:
   * question:
   *  in the Component under Autowired's bean is null,
   *
   * solution:
   *  https://blog.csdn.net/georgeshaw1/article/details/74943089 (source)
   *  https://zakariyya.github.io/2018/08/23/backEnd/spring/springboot-Component%E4%B8%8B-Autowired%E7%9A%84%E6%B3%A8%E5%85%A5%E4%B8%BAnull/#%E8%83%8C%E6%99%AF  (backup)
   *  https://zakariyya.github.io/2018/08/23/backEnd/spring/springboot-Component下-Autowired的注入为null/#背景  (backup)
 *
 */
@Component
public class Comment2CommentDto {

  @Autowired
  protected CommentService service;
  private static Comment2CommentDto  c2c ;
  @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
  public void init() {
    c2c = this;
    c2c.service = this.service;
    // 初使化时将已静态化的testService实例化
  }

  public CommentDto convert(Comment data) {
    CommentDto dto = new CommentDto();
    if(null != data.getParentId()){
      dto.setParent(c2c.service.findOne(data.getParentId()));
    }
    dto.setId(data.getId());
    dto.setContent(data.getContent());
    dto.setCreateTime(data.getCreateTime());
    dto.setUpdateTime(data.getUpdateTime());
    return dto;
  }


}
