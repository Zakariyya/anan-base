package com.urundp.corona.file.service.impl;

import com.urundp.corona.file.dao.HDirectoryRepository;
import com.urundp.corona.file.dao.HFileRepository;
import com.urundp.corona.file.dot.HDirectoryDto;
import com.urundp.corona.file.dot.HFileDto;
import com.urundp.corona.file.enums.GlobalEnum;
import com.urundp.corona.file.model.HDirectory;
import com.urundp.corona.file.model.HFile;
import com.urundp.corona.file.service.HDirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@Service
@Slf4j
@Transactional
public class HDirectoryServiceImpl implements HDirectoryService {

  private final HDirectoryRepository hDirectoryRepository;
  private final HFileRepository hFileRepository;

  @Autowired
  public HDirectoryServiceImpl(HDirectoryRepository hDirectoryRepository, HFileRepository hFileRepository) {
    this.hDirectoryRepository = hDirectoryRepository;
    this.hFileRepository = hFileRepository;
  }

  @Override
  public HDirectory save(HDirectory data) {
    return hDirectoryRepository.save(data);
  }

  @Override
  public GlobalEnum delete(String id) {
    String[] ids = id.split(",");
    GlobalEnum result = GlobalEnum.SUCCESS;
    for (String sid : ids) {
      if (null == hDirectoryRepository.findAllByParent(new HDirectory(Integer.parseInt(sid)))) {
        hDirectoryRepository.deleteById(Integer.parseInt(sid));
      }else{
        result = GlobalEnum.PARTIALFAILED;
      }
    }
    return result;
  }

  /**
   *
   * @param id "1,2,3,4,5"
   * @param force 是否强制删除，递归删除文件和文件夹
   * @return 返回全局状态枚举类
   */
  @Override
  public GlobalEnum delete(String id, Boolean force) {
    GlobalEnum result = GlobalEnum.SUCCESS;
    if(force){
      String[] ids = id.split(",");
      //TODO 这里需要递归 删除文件夹 和绑定的文件
      for (String sid : ids) System.out.println("==" + sid);

    }else{
      result = delete(id);
    }
    return result;
  }

  @Override
  public List<HDirectory> findAll() {
    return hDirectoryRepository.findAll();
  }

  @Override
  public List<HDirectory> findAllRoot() {
    return hDirectoryRepository.findAllByParentIsNull();
  }

  @Override
  public HDirectoryDto findOne(Integer id) {

    /*
      所需变量声明
     */
    HDirectoryDto data = new HDirectoryDto();
    HDirectoryDto dataHDDto;
    HFileDto dataHFDto;
    List<HDirectoryDto> listHD = new ArrayList<>();
    List<HFileDto> listHF = new ArrayList<>();

    /*
     * data 赋值
     */
    HDirectory hd = hDirectoryRepository.getOne(id);
    BeanUtils.copyProperties(hd,data);
    if(null != hd.getParent()) {//根目录
      data.setParentId(hd.getParent().getId());
    }

    //不是根目录
    //声明DTO 列表
    log.info("========="+hd.getId());
    List<HDirectory> HDList = hDirectoryRepository.findAllByParent(hd);
    //复制属性
    for (HDirectory item : HDList) {
      dataHDDto=new HDirectoryDto();
      BeanUtils.copyProperties(item,dataHDDto);
      listHD.add(dataHDDto);
    }


    /*
     * 声明 DTO 列表
     */
    List<HFile> HFList = hFileRepository.findAllByHDirectory(hd);
    /*
     * 复制属性
     */
    for (HFile item : HFList) {
      dataHFDto=new HFileDto();
      BeanUtils.copyProperties(item,dataHFDto);
      listHF.add(dataHFDto);
    }

    /*
     * 赋值
     */
    data.setDirList(listHD);
    data.setFileList(listHF);

    return data;

  }

  @Override
  public Page<HDirectory> findAll(Pageable pageable) {
    return hDirectoryRepository.findAll(pageable);
  }

  @Override
  public List<HDirectory> findAllByNameAndAndParent(String name, Integer parentId) {
    List<HDirectory> result;
    if(null == parentId){
      result = hDirectoryRepository.findAllByNameAndAndParentIsNull(name);
    }else{
      result = hDirectoryRepository.findAllByNameAndAndParent(name, parentId);
    }
    return result;
  }
}
