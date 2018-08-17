package com.urundp.corona.file.controller;

import com.urundp.corona.common.utils.CommonFunctions;
import com.urundp.corona.common.utils.DataResult;
import com.urundp.corona.common.utils.DataResultOfPage;
import com.urundp.corona.file.dao.BusinessCategoryRepository;
import com.urundp.corona.file.enums.GlobalEnum;
import com.urundp.corona.file.model.BusinessCategory;
import com.urundp.corona.file.service.BusinessCategoryService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */

@RestController
@RequestMapping("/businesscategory")
@Slf4j
public class BusinessCategoryController {

  private final BusinessCategoryService businessCategoryService;
  private final BusinessCategoryRepository businessCategoryRepository;

  @Autowired
  public BusinessCategoryController(BusinessCategoryService businessCategoryService, BusinessCategoryRepository businessCategoryRepository) {
    this.businessCategoryService = businessCategoryService;
    this.businessCategoryRepository = businessCategoryRepository;
  }

  /**
   * 列表页（分页）
   *
   * //=="+all.getTotalElements());  //总元素
   * //=="+all.getTotalPages()); //总页数
   * //=="+all.getNumber());  //数， 第0页
   * //=="+all.getSort());  //分类
   * //=="+all.getNumberOfElements());  //元素数量
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
    Page<BusinessCategory> all = businessCategoryService.findAll(request);
    return CommonFunctions.result(true, all.getContent(), GlobalEnum.SUCCESS.getMessage(), null,all.getNumberOfElements());
  }

  /**
   * 列表页
   * @return 返回结果集
   */
  @RequestMapping(value = "findAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> findAll(){
    return CommonFunctions.result(true, businessCategoryService.findAll(), GlobalEnum.SUCCESS.getMessage(), null);
  }


  /**
   * 获取单个
   * @param id 主键
   * @return 返回结果集
   */
  @RequestMapping(value = "findOne", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> findOne(@RequestParam(value = "id") Integer id){
    return CommonFunctions.result(true, businessCategoryService.findOne(id), GlobalEnum.SUCCESS.getMessage(), null);
  }

  /**
   * 新增
   *
   * @param data:BusinessCategory pojo
   * @return 返回结果集
   */
  @RequestMapping(value = "save", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> save(@RequestBody BusinessCategory data){
    if(null == data.getId()){
      data.setCreateTime(new Timestamp(System.currentTimeMillis()));
    }else{
      data.setCreateTime(new Timestamp(businessCategoryRepository.getOne(data.getId()).getCreateTime()));
    }
    val save = businessCategoryService.save(data);
    return CommonFunctions.result(true, save, GlobalEnum.SUCCESS.getMessage(), null);
  }

  /**
   * 删除/批量删除
   * @param params {"id":"1,2,3,4"}
   * @return 返回结果集
   */
  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public DataResult<Object> batchApply(@RequestBody Map<String, String> params) {
    businessCategoryService.delete(params.get("id"));
    return CommonFunctions.result(true, null, GlobalEnum.SUCCESS.getMessage(), null);
  }






}
