package anan.base.core.service;

import anan.base.core.orm.DictOption;
import java.util.List;

/**
 * @author anan
 * Created on 2018/8/24.
 */

public interface DictOptionService{

  /**
   * 查找某一种类型的字典列表
   *
   */
  List<DictOption> listByTypeForRelation(String dicType);

  List<DictOption> findAllByDictTypeAndOptionValue(String dicType,String optionValue);

  List<DictOption> findAllByDictTypeAndK1(String dicType,String k1);

  List<DictOption> findAllByDictTypeAndK2(String dicType,String k2);

  DictOption save(DictOption data);


}