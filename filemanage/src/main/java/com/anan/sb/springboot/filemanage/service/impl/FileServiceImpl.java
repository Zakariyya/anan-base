package com.anan.sb.springboot.filemanage.service.impl;

import com.anan.sb.springboot.filemanage.exception.FileException;
import com.anan.sb.springboot.filemanage.form.FileForm;
import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.orm.core.ResponseResult;
import com.anan.sb.springboot.filemanage.repository.FileRepository;
import com.anan.sb.springboot.filemanage.service.FileService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author anan
 * Created on 2018/8/8.
 */
@Service
@Slf4j
@Transactional
@Data
public class FileServiceImpl implements FileService {

  @Autowired
  private FileRepository fileRepository ;
//  private FileRepository fileRepository ;

//  @Autowired
//  public FileServiceImpl(FileRepository fileRepository) {
//    this.fileRepository = fileRepository;
//  }

  /**
   * @param data File
   * @return File
   */
  @Override
  public File save(FileForm data) {
    return fileRepository.save(data);
  }

  /**
   * 如果出现parent引用，则不允许删除
   * @param id [1,2,3,4.5]
   */
  @Override
  public void delete(String id, ResponseResult result) {
    String[] ids = id.split(",");
    //做循环删除，若文件删除失败报出异常直接 略过处理，不做回滚
    for (String sid : ids) {
      try{
        fileRepository.deleteById(Integer.parseInt(sid));
      }catch (FileException e){
        val findOne = fileRepository.getOne(Integer.parseInt(sid));
        result.addMessage("文件名为"+findOne.getName()+"删除失败");
      }
    }
  }

  /**
   * 强制删除，这里做递归查询删除
   * @param id [1,2,3,4.5]
   * @param force 是否强制删除 true/false
   */
  @Override
  public void delete(String id, Boolean force,ResponseResult result) {
    if(force)
      //TODO 强制删除
      log.info("delete -f :"+id);
    else
      delete(id,result);
  }

  @Override
  public List<File> findAll() {
    return fileRepository.findAll();
  }

  @Override
  public File findOne(Integer id) {
    return fileRepository.findById(id).get();
  }


}
