package anan.base.content.service.impl;

import anan.base.content.orm.Category;
import anan.base.content.form.CategoryForm;
import anan.base.content.repository.CategoryRepository;
import anan.base.content.service.CategoryService;
import anan.base.core.exception.CoreException;
import anan.base.core.orm.DictOption;
import anan.base.core.orm.ResponseResult;
import anan.base.core.repository.DictOptionRepository;
import anan.base.core.util.VerifyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
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
  public Page<Category> findAll(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }

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
