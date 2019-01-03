package anan.base.comment.service;

import anan.base.comment.orm.Comment;
import anan.base.core.orm.ResponseResult;

import java.util.List;

/**
 * @author anan
 * Created on 2018/8/22.
 */
public interface CommentService {

  List<Comment> findAll();

  Comment findOne(String uuid);

  Comment save (Comment data);

  Integer update (Comment data, ResponseResult result);

  Comment delete(String uuid, ResponseResult result);



}
