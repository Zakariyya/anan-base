package com.urundp.corona.file.service;

import com.urundp.corona.file.dot.HDirectoryDto;
import com.urundp.corona.file.enums.GlobalEnum;
import com.urundp.corona.file.model.HDirectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/8.
 */
public interface HDirectoryService {

  HDirectory save(HDirectory data);

  GlobalEnum delete(String id);

  GlobalEnum delete(String id, Boolean force);

  List<HDirectory> findAll();

  //查找根目录
  List<HDirectory> findAllRoot();

  HDirectoryDto findOne(Integer id);

  Page<HDirectory> findAll(Pageable pageable);

  List<HDirectory> findAllByNameAndAndParent(String name , Integer parentId);
}
