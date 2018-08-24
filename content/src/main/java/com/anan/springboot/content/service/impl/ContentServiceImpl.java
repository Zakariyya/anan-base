package com.anan.springboot.content.service.impl;

import com.anan.springboot.content.form.ContentForm;
import com.anan.springboot.content.orm.Content;
import com.anan.springboot.content.repository.CategoryRepository;
import com.anan.springboot.content.repository.ContentRepository;
import com.anan.springboot.content.service.ContentService;
import com.anan.springboot.core.orm.ResponseResult;
import com.anan.springboot.core.repository.DictOptionRepository;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Content> findAll() {
    return contentRepository.findAll();
  }

  @Override
  public Content findOne(String id) {
    return contentRepository.findById(id).get();
  }

  @Override
  public Content save(ContentForm form) {

    Content data = new Content();
    BeanUtils.copyProperties(form,data);
    val category = categoryRepository.findById(form.getCategoryId()).get();
    data.setCategory(category);

    return contentRepository.save(data);
  }

  @Override
  public Content update(ContentForm form, ResponseResult result) {
    return save(form);
  }

  @Override
  public void delete(String id, ResponseResult result) {
    contentRepository.deleteById(id);

  }
}
