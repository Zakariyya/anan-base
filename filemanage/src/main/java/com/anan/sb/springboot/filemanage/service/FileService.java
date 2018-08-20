package com.anan.sb.springboot.filemanage.service;

import com.anan.sb.springboot.filemanage.form.FileForm;
import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.orm.core.ResponseResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.ResourceBundle;

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
  File save(FileForm data);

  File update(FileForm data,ResponseResult result);

  /**
   *
   * @param id
   * @return
   */
  void delete(String id,ResponseResult result);

  void delete(String id, Boolean force, ResponseResult result);

  List<File> findAll();

  File findOne(Integer id);




}
