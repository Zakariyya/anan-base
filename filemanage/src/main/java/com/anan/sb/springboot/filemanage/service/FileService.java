package com.anan.sb.springboot.filemanage.service;

import com.anan.sb.springboot.filemanage.orm.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author anan
 * Created on 2018/8/8.
 */
public interface FileService {

  /**
   * 新增/编辑 文件夹 && 上传/编辑 文件
   * @param data
   * @return
   */
  File save(File data);

  /**
   *
   * @param id
   * @return
   */
  String delete(String id);

  String delete(String id, Boolean force);

  List<File> findAll();

  File findOne(Integer id);




}
