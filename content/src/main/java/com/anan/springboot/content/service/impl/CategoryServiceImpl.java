package com.anan.springboot.content.service.impl;

import com.anan.springboot.content.form.CategoryForm;
import com.anan.springboot.content.orm.Category;
import com.anan.springboot.content.repository.CategoryRepository;
import com.anan.springboot.content.service.CategoryService;
import com.anan.springboot.core.exception.CoreException;
import com.anan.springboot.core.orm.DictOption;
import com.anan.springboot.core.orm.ResponseResult;
import com.anan.springboot.core.repository.DictOptionRepository;
import com.anan.springboot.core.util.VerifyForm;
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
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private DictOptionRepository dictOptionRepository;

  @Autowired
  private VerifyForm verifyForm;

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category findOne(Integer id) {
    return categoryRepository.findById(id).get();
  }

  @Override
  public Category save(CategoryForm form) {
    Category data = new Category();
    DictOption categoryType = dictOptionRepository.findById(form.getCategoryId()).get();
    data.setCategoryType(categoryType);
    if (null != form.getParentId()) {
      data.setParent(findOne(form.getParentId()));
    }
    data.setName(form.getName());
    data.setRemark(form.getName());
    data.setRemark(form.getRemark());


    return categoryRepository.save(data);
  }

  @Override
  public Category update(CategoryForm form, ResponseResult result) {

    Category data = findOne(form.getId());
    verifyForm.dataIsNullOrFormParentIdEqId(data,form,result);
    if(result.hasErrors()){
      return null;
    }

    if(null != form.getParentId()){
      data.setParent(findOne(form.getParentId()));
    }
    data.setRemark(form.getRemark());
    data.setName(form.getName());


    return categoryRepository.save(data);
  }

  @Override
  public void delete(String id, ResponseResult result) {
    String[] ids = id.split(",");
    for (String sid : ids) {
      try{
        if(categoryRepository.findAllByParent(new Category(Integer.parseInt(sid))).size() == 0){
          categoryRepository.deleteById(Integer.parseInt(sid));
        }
        result.addMessage("文件夹名为"+categoryRepository.findById(Integer.parseInt(sid)).get().getName()+"，存在下级，删除失败");
      }catch (CoreException e){
        result.addMessage("文件名为"+categoryRepository.findById(Integer.parseInt(sid)).get().getName()+"删除失败");
      }
    }
  }



}
