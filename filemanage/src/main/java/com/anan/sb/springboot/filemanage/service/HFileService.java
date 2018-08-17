package com.urundp.corona.file.service;

import com.urundp.corona.file.model.HFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */
public interface HFileService {

  HFile save(HFile data);

  boolean delete(String id);

  List<HFile> findAll();

  HFile findOne(Integer id);

  Page<HFile> findAll(Pageable pageable);


}
