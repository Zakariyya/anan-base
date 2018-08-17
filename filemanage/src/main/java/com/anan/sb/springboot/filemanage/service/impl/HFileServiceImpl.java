package com.urundp.corona.file.service.impl;

import com.urundp.corona.file.dao.HFileRepository;
import com.urundp.corona.file.model.HFile;
import com.urundp.corona.file.service.HFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@Service
@Slf4j
@Transactional
public class HFileServiceImpl implements HFileService {

  private final HFileRepository hFileRepository;

  @Autowired
  public HFileServiceImpl(HFileRepository hFileRepository) {
    this.hFileRepository = hFileRepository;
  }

  @Override
  public HFile save(HFile data) {
    return hFileRepository.save(data);
  }

  @Override
  public boolean delete(String id) {
    String[] ids = id.split(",");
    for (String sid : ids) {
      hFileRepository.deleteById(Integer.parseInt(sid));
    }
    return true;
  }


  @Override
  public List<HFile> findAll() {
    return hFileRepository.findAll();
  }

  @Override
  public HFile findOne(Integer id) {
    return hFileRepository.findById(id).get();
  }

  @Override
  public Page<HFile> findAll(Pageable pageable) {
    return hFileRepository.findAll(pageable);
  }

}
