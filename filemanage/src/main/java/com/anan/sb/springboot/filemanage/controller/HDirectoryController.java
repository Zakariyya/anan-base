package com.urundp.corona.file.controller;

import com.urundp.corona.common.utils.CommonFunctions;
import com.urundp.corona.common.utils.DataResult;
import com.urundp.corona.common.utils.DataResultOfPage;
import com.urundp.corona.file.dot.HDirectoryDto;
import com.urundp.corona.file.enums.GlobalEnum;
import com.urundp.corona.file.model.HDirectory;
import com.urundp.corona.file.service.HDirectoryService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@RestController
@RequestMapping("/hdirectory")
@Slf4j
public class HDirectoryController {

  private final HDirectoryService hDirectoryService;
  @Autowired
  public HDirectoryController(HDirectoryService hDirectoryService) {
    this.hDirectoryService = hDirectoryService;
  }


  /**
   * 列表页（分页）
   * =="+all.getTotalElements());  //总元素
   * =="+all.getTotalPages()); //总页数
   * =="+all.getNumber());  //数， 第0页
   * =="+all.getSort());  //分类
   * =="+all.getNumberOfElements());  //元素数量
   * =="+all.getContent());  //page的内容
   *
   * @param page page
   * @param size size
   * @return DataResultOfPage
   */
  @RequestMapping(value = "findAllList", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResultOfPage<Object> findAllList(
          @RequestParam(value = "page",defaultValue = "1") Integer page,
          @RequestParam(value = "size",defaultValue = "10") Integer size){
    val request = PageRequest.of(page - 1, size);
    Page<HDirectory> all = hDirectoryService.findAll(request);
    return CommonFunctions.result(true, all.getContent(), GlobalEnum.SUCCESS.getMessage(), null,all.getNumberOfElements());
  }

  /**
   * 列表页
   * @return 返回整个列表结果集
   */
  @RequestMapping(value = "findAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> findAll(@RequestParam(value = "root",defaultValue = "false") Boolean root){
    if(root){
      return CommonFunctions.result(true, hDirectoryService.findAllRoot(), GlobalEnum.SUCCESS.getMessage(), null);
    }
    return CommonFunctions.result(true, hDirectoryService.findAll(), GlobalEnum.SUCCESS.getMessage(), null);
  }
  /**
   * 获取单个
   * @param id 主键
   * @return 返回结果集
   */
  @RequestMapping(value = "findOne", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> findOne(@RequestParam(value = "id") Integer id){
    return CommonFunctions.result(true, hDirectoryService.findOne(id), GlobalEnum.SUCCESS.getMessage(), null);
  }

  /**
   * 新增/更新
   *
   * @param dto:HDirectoryDto pojo
   * @return 返回结果集
   */
  @RequestMapping(value = "save", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> save(@RequestBody HDirectoryDto dto){
    HDirectory data = new HDirectory();
    if(null != dto.getParentId()){
      data.setParent(new HDirectory(dto.getParentId()));
    }
    val list = hDirectoryService.findAllByNameAndAndParent(dto.getName(),dto.getParentId());
    if(list.size() != 0 ){
      if(null != data.getId()){
        if(!list.get(0).getId().equals(data.getId())){
          return CommonFunctions.result(false, null, "存在重复的文件夹名称", null);
        }
      }else{
        return CommonFunctions.result(false, null, "存在重复的文件夹名称", null);
      }
    }
    BeanUtils.copyProperties(dto,data);
    val save = hDirectoryService.save(data);
    return CommonFunctions.result(true, save, GlobalEnum.SUCCESS.getMessage(), null);
  }


  /**
   * 删除/批量删除
   * @param params {id:"1,2,3,4,54"}
   * @return 返回结果集
   */
  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public DataResult<Object> batchApply(@RequestBody Map<String, String> params) {
    hDirectoryService.delete(params.get("id"));
    return CommonFunctions.result(true, null, GlobalEnum.SUCCESS.getMessage(), null);
  }

}
