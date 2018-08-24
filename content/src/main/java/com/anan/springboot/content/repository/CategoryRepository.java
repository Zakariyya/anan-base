package com.anan.springboot.content.repository;

import com.anan.springboot.content.orm.Category;
import com.anan.springboot.core.orm.DictOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */
public interface CategoryRepository  extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<DictOption> {

  List<Category> findAllByParent(Category parent);

}
