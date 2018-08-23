package com.anan.springboot.comment.service.impl;

import com.anan.springboot.comment.orm.Comment;
import com.anan.springboot.comment.repository.CommentRepository;
import com.anan.springboot.comment.service.CommentService;
import com.anan.springboot.core.orm.ResponseResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  @Override
  public Comment findOne(String uuid) {
    return commentRepository.findAllById(uuid);
  }

  @Override
  public Comment save(Comment data) {
    data.setId(UUID.randomUUID().toString());
    return commentRepository.save(data);
  }

  @Override
  public Integer update(Comment data, ResponseResult result) {
    return commentRepository.updateContentById(data.getContent(),data.getId());
  }

  @Override
  public Comment delete(String uuid, ResponseResult result) {
    commentRepository.deleteById(uuid);
    return null;
  }
}
