package com.anan.springboot.core.repository;

import com.anan.springboot.core.orm.DictOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.zip.DeflaterInputStream;

/**
 * @author anan
 * Create on 2018/8/19
 */
public interface  DictOptionRepository extends JpaRepository<DictOption, Integer>{

  List<DictOption> findAllByDictType(String dictType);

  List<DictOption> findAllByDictTypeAndOptionValue(String dictType, String optionValue);

  List<DictOption> findAllByDictTypeAAndK1(String dictType, String k1);

  List<DictOption> findAllByDictTypeAAndK2(String dictType, String k2);





}
