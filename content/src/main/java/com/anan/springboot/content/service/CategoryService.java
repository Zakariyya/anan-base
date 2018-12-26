package com.anan.springboot.content.service;

import com.anan.springboot.content.form.CategoryForm;
import com.anan.springboot.content.orm.Category;
import com.anan.springboot.core.orm.ResponseResult;

import java.util.List;

/**
 * @author anan
 * Created by anan on 2018/8/24.
 */
public interface CategoryService {

  List<Category> findAll();

  Category findOne(Integer id);

  Category save (CategoryForm data);

  Category update (CategoryForm data, ResponseResult result);

  void delete(String id, ResponseResult result);



}
