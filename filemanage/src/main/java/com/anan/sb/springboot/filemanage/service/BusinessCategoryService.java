package com.urundp.corona.file.service;

import com.urundp.corona.file.model.BusinessCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/8.
 */
public interface BusinessCategoryService {

  BusinessCategory save(BusinessCategory data);

  boolean delete(String id);

  List<BusinessCategory> findAll();

  BusinessCategory findOne(Integer id);

  Page<BusinessCategory> findAll(Pageable pageable);

}
