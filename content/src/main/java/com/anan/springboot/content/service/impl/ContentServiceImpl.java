package com.anan.springboot.content.service.impl;

import com.anan.springboot.content.orm.Content;
import com.anan.springboot.content.repository.ContentRepository;
import com.anan.springboot.content.service.ContentService;
import com.anan.springboot.core.orm.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

  @Autowired
  private ContentRepository contentRepository;

  @Override
  public List<Content> findAll() {
    return contentRepository.findAll();
  }

  @Override
  public Content findOne(String id) {
    return contentRepository.findById(id).get();
  }

  @Override
  public Content save(Content data) {
    return contentRepository.save(data);
  }

  @Override
  public Content update(Content data, ResponseResult result) {
    return contentRepository.save(data);
  }

  @Override
  public void delete(String id, ResponseResult result) {
    contentRepository.deleteById(id);

  }
}
