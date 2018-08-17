package com.urundp.corona.file.service.impl;

import com.urundp.corona.file.dao.BusinessCategoryRepository;
import com.urundp.corona.file.model.BusinessCategory;
import com.urundp.corona.file.service.BusinessCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@Service
@Slf4j
@Transactional
public class BusinessCategoryServiceImpl implements BusinessCategoryService {


  private final BusinessCategoryRepository businessCategoryRepository;

  @Autowired
  public BusinessCategoryServiceImpl(BusinessCategoryRepository businessCategoryRepository) {
    this.businessCategoryRepository = businessCategoryRepository;
  }

  @Override
  public BusinessCategory save(BusinessCategory data) {
    return businessCategoryRepository.save(data);
  }

  @Override
  public boolean delete(String id) {
    String[] ids = id.split(",");
    for (String sid : ids) {
      businessCategoryRepository.deleteById(Integer.parseInt(sid));
    }
    return true;
  }

  @Override
  public List<BusinessCategory> findAll() {
    return businessCategoryRepository.findAll();
  }

  @Override
  public BusinessCategory findOne(Integer id) {
    return businessCategoryRepository.getOne(id);
  }

  @Override
  public Page<BusinessCategory> findAll(Pageable pageable) {
    return businessCategoryRepository.findAll(pageable);
  }
}
