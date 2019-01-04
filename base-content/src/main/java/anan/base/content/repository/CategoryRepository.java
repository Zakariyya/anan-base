package anan.base.content.repository;

import anan.base.content.orm.Category;
import anan.base.core.orm.DictOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author anan
 * Created on 2018/8/24.
 */
public interface CategoryRepository  extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<DictOption> {

  List<Category> findAllByParent(Category parent);

}
