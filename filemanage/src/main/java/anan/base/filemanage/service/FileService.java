package anan.base.filemanage.service;

import anan.base.core.orm.ResponseResult;
import anan.base.filemanage.form.FileForm;
import anan.base.filemanage.orm.File;

import java.util.List;

/**
 * @author anan
 * Created on 2018/8/8.
 */
public interface FileService {

  /**
   * save file dir
   * @param data FileForm
   * @return File: but I find it cant return a result from the database
   */
  File save(FileForm data);

  /**
   * update
   * @param data FileForm
   * @param result 4 message or error flag
   * @return File
   */
  File update(FileForm data, ResponseResult result);

  void delete(String id,ResponseResult result);

  void delete(String id, Boolean force, ResponseResult result);

  List<File> findAll();

  File findOne(Integer id);




}
