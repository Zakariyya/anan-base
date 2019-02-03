package anan.base.content.service;

import anan.base.content.form.CategoryForm;
import anan.base.content.orm.Category;
import anan.base.core.orm.ResponseResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author anan
 * Created by anan on 2018/8/24.
 */
public interface CategoryService {

  Page<Category> findAll(Pageable pageable);

  List<Category> findAll();

  Category findOne(Integer id);

  Category save (CategoryForm data);

  Category update (CategoryForm data, ResponseResult result);

  void delete(String id, ResponseResult result);



}
