package com.anan.springboot.content.service;

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

  Content save (Content data);

  Content update (Content data, ResponseResult result);

  void delete(String id, ResponseResult result);

}
