package com.anan.springboot.core.service.impl;

import com.anan.springboot.core.orm.DictOption;
import com.anan.springboot.core.repository.DictOptionRepository;
import com.anan.springboot.core.service.DictOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
 * Create on 2018/8/25
 */
@Service
@Transactional
public class DictOptionServiceImpl implements DictOptionService {
  @Autowired
  private DictOptionRepository dictOptionRepository;

  @Override
  public List<DictOption> listByTypeForRelation(String dicType) {
    DictOption data = new DictOption();
    data.setDictType(dicType);
    return dictOptionRepository.findAllByDictType(dicType);
  }

  @Override
  public List<DictOption> findAllByDictTypeAndOptionValue(String dicType, String optionValue) {
    DictOption data = new DictOption();
    data.setDictType(dicType);
    data.setOptionValue(optionValue);
    return dictOptionRepository.findAllByDictTypeAndOptionValue(dicType, optionValue);
  }

  @Override
  public List<DictOption> findAllByDictTypeAndK1(String dicType,String k1) {
    DictOption data = new DictOption();
    data.setDictType(dicType);
    data.setK1(k1);
    return dictOptionRepository.findAllByDictTypeAndK1(dicType,k1);
  }

  @Override
  public List<DictOption> findAllByDictTypeAndK2(String dicType,String k2) {
    DictOption data = new DictOption();
    data.setDictType(dicType);
    data.setK2(k2);
    return dictOptionRepository.findAllByDictTypeAndK2(dicType,k2);
  }

  @Override
  public DictOption save(DictOption data) {
    //the DictOption.dict_type is exist in the config.
    return dictOptionRepository.save(data);
  }
}
