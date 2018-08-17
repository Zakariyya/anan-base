package com.urundp.corona.file.dao;

import com.urundp.corona.file.model.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/8.
 */

public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Integer> , JpaSpecificationExecutor<BusinessCategory> {

}
