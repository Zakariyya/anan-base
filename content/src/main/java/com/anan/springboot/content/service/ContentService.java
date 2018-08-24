package com.anan.springboot.content.service;

import com.anan.springboot.content.form.ContentForm;
import com.anan.springboot.content.orm.Content;
import com.anan.springboot.core.orm.ResponseResult;

import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */
public interface ContentService {

  List<Content> findAll();

  Content findOne(String id);

  Content save (ContentForm data);

  Content update (ContentForm data, ResponseResult result);

  void delete(String id, ResponseResult result);

}
