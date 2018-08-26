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
    return dictOptionRepository.findAllByDictType(dicType);
  }

  @Override
  public List<DictOption> findAllByDictTypeAndOptionValue(String dicType, String optionValue) {
    return dictOptionRepository.findAllByDictTypeAndOptionValue(dicType,optionValue );
  }

  @Override
  public List<DictOption> findAllByDictTypeAAndK1(String dicType,String k1) {
    return dictOptionRepository.findAllByDictTypeAAndK1(dicType,k1);
  }

  @Override
  public List<DictOption> findAllByDictTypeAAndK2(String dicType,String k2) {
    return dictOptionRepository.findAllByDictTypeAAndK2(dicType,k2);
  }
}
