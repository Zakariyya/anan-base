package anan.base.filemanage.service.impl;

import anan.base.core.exception.CoreException;
import anan.base.core.orm.ResponseResult;
import anan.base.core.repository.DictOptionRepository;
import anan.base.core.util.VerifyForm;
import anan.base.filemanage.form.FileForm;
import anan.base.filemanage.orm.File;
import anan.base.filemanage.repository.FileRepository;
import anan.base.filemanage.service.FileService;
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

  @Autowired
  private DictOptionRepository dictOptionRepository;

  @Autowired
  private VerifyForm verifyForm;

  /**
   * @param form FileForm
   * @return File
   */
  @Override
  public File save(FileForm form) {
    File data = new File();

    val fileType = dictOptionRepository.findById(form.getFileTypeId()).get();
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
    verifyForm.dataIsNullOrFormParentIdEqId(data,form,result);
    if(result.hasErrors()){
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
    //foreach delete, if failure? jump in catch add message,	and then continue
    for (String sid : ids) {
      try{
        if(fileRepository.findAllByParent(new File(Integer.parseInt(sid))).size() == 0){
          fileRepository.deleteById(Integer.parseInt(sid));
        }
        result.addMessage("文件夹名为"+fileRepository.findById(Integer.parseInt(sid)).get().getName()+"，存在下级，删除失败");
      }catch (CoreException e){
        result.addMessage("文件名为"+fileRepository.findById(Integer.parseInt(sid)).get().getName()+"删除失败");
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
