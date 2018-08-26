package com.anan.springboot.core.service;

import com.anan.springboot.core.orm.DictOption;
import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */

public interface DictOptionService{

  /**
   * 查找某一种类型的字典列表
   *
   */
  List<DictOption> listByTypeForRelation(String dicType);

  List<DictOption> findAllByDictTypeAndOptionValue(String dicType,String optionValue);

  List<DictOption> findAllByDictTypeAAndK1(String dicType,String k1);

  List<DictOption> findAllByDictTypeAAndK2(String dicType,String k2);


}