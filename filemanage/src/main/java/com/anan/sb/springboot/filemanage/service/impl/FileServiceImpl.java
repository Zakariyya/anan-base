package com.anan.sb.springboot.filemanage.service.impl;

import com.anan.sb.springboot.filemanage.exception.FileException;
import com.anan.sb.springboot.filemanage.form.FileForm;
import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.repository.FileRepository;
import com.anan.sb.springboot.filemanage.service.FileService;
import com.anan.springboot.core.orm.DictOption;
import com.anan.springboot.core.orm.ResponseResult;
import com.anan.springboot.core.repository.DictOptionRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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

  @Autowired
  private DictOptionRepository dictOptionRepository;

  /** @deprecated save
   * @param form FileForm
   * @return File
   */
  @Override
  public File save(FileForm form) {
    File data = new File();

    DictOption fileType = dictOptionRepository.getOne(form.getFileTypeId());
    data.setFileType(fileType);
    if (null != form.getParentId()) {
      data.setParent(findOne(form.getParentId()));
    }
    data.setName(form.getName());
    data.setRemark(form.getName());

    return fileRepository.save(data);
  }

  @Override
  public File update(FileForm form, ResponseResult result) {

    File data = findOne(form.getId());
    if (null == data) {
      result.addError("更新失败，无该文件");
      return null;
    }else if(form.getParentId() == form.getId()){
      result.addError("更新失败，你想多了，自己不能做自己的爸爸");
      return null;
    }
    if(null != form.getParentId()){
      data.setParent(findOne(form.getParentId()));
    }
    data.setRemark(form.getRemark());
    data.setName(form.getName());

    return fileRepository.save(data);
  }

  /**
   * no delete, if it have a children
   * @param id "1,2,3,4.5"
   */
  @Override
  public void delete(String id, ResponseResult result) {
    String[] ids = id.split(",");
    //做循环删除，若文件删除失败报出异常直接 略过处理，不做回滚
    //foreach delete, if failure? jump in catch add message,	and then continue
    for (String sid : ids) {
      try{
        if(fileRepository.findAllByParent(new File(Integer.parseInt(sid))).size() == 0){
          fileRepository.deleteById(Integer.parseInt(sid));
        }
        result.addMessage("文件夹名为"+fileRepository.getOne(Integer.parseInt(sid)).getName()+"，存在下级，删除失败");
      }catch (FileException e){
        result.addMessage("文件名为"+fileRepository.getOne(Integer.parseInt(sid)).getName()+"删除失败");
      }
    }
  }

  /**
   * force delete , true/false
   * @param id [1,2,3,4.5]
   * @param force delete -f ? true/false
   */
  @Override
  public void delete(String id, Boolean force,ResponseResult result) {
    if(force)
      //TODO 强制删除,递归
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
