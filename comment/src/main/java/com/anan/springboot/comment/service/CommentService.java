package com.anan.springboot.comment.service;

import com.anan.springboot.comment.orm.Comment;
import com.anan.springboot.core.orm.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
public interface CommentService {

  List<Comment> findAll();


  Comment findOne(String uuid);

  Comment save (Comment data);

  Integer update (Comment data, ResponseResult result);

  Comment delete(String uuid, ResponseResult result);


}
