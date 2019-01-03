package anan.base.core.repository;

import anan.base.core.orm.DictOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author anan
 * Create on 2018/8/19
 */
public interface  DictOptionRepository extends JpaRepository<DictOption, Integer>{

  List<DictOption> findAllByDictType(String dictType);

  List<DictOption> findAllByDictTypeAndOptionValue(String dictType, String optionValue);

  List<DictOption> findAllByDictTypeAndK1(String dictType, String k1);

  List<DictOption> findAllByDictTypeAndK2(String dictType, String k2);


/**
 * List<DictOption> findAllByDictType(DictOption dictType);

 List<DictOption> findAllByDictTypeAndOptionValue(DictOption dictTypeAndOptionValue);

 List<DictOption> findAllByDictTypeAAndK1(DictOption dictTypeAndK1);

 List<DictOption> findAllByDictTypeAAndK2(DictOption dictTypeAndK2);
 */


}
