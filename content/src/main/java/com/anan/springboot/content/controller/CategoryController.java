package com.anan.springboot.content.controller;

import com.anan.springboot.content.ContentTable;
import com.anan.springboot.content.form.CategoryForm;
import com.anan.springboot.content.orm.Category;
import com.anan.springboot.content.service.CategoryService;
import com.anan.springboot.core.enums.EnabledEnum;
import com.anan.springboot.core.enums.ResultEnum;
import com.anan.springboot.core.exception.CoreException;
import com.anan.springboot.core.orm.ResponseResult;
import com.anan.springboot.core.service.DictOptionService;
import com.anan.springboot.core.util.ResultVOUtil;
import com.anan.springboot.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
  @Autowired
  private DictOptionService dictOptionService;

  /**
   * findAll
   * @return ResultVO<Category>
   */
  @GetMapping("")
  public ResultVO findAll(){
    List<Category> all = categoryService.findAll();
    return ResultVOUtil.success(all);
  }

  /**
   * findOne
   * @param id primary key
   * @return ResultVO
   */
  @GetMapping("/{id}")
  public ResultVO findOne(@PathVariable("id") Integer id){
    return ResultVOUtil.success(categoryService.findOne(id));
  }

  /**
   * save
   * @param data :CategoryDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultVO save(@Valid @RequestBody CategoryForm data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【类别管理】参数不正确, CategoryDto={}", data);
      throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    categoryService.save(data);
    return ResultVOUtil.success();
  }

  /**
   * update info
   * @param data :FileForm pojo
   * @return ResultVO
   */
  @PutMapping("/{id}")
  public ResultVO update(@Valid @RequestBody CategoryForm data, @PathVariable("id") Integer id, BindingResult bindingResult){
    if (bindingResult.hasErrors() ){
      log.error("【类别管理】参数不正确, CategoryDto={}", data);
      throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    ResponseResult result = new ResponseResult();
    data.setId(id);
    categoryService.update(data, result);
    if(result.hasErrors())
      return ResultVOUtil.error(ResultEnum.FAILURE.getCode(), result.getMessage());
    return ResultVOUtil.success();

  }


  /**
   * batch delete
   * @param id // @RequestBody Map<String,String> param {"id":"1,2,3,4,5"}
   *           // @PathVariable String id :"1,2,3,4,5"
   * @return ResultVO
   */
  @ResponseBody
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResultVO delete(@PathVariable("id") String id) {
    ResponseResult result = new ResponseResult();
    categoryService.delete(id,result);
    if(result.hasMessages())
      return ResultVOUtil.error(ResultEnum.DELETE_SECTION.getCode(), result.getMessage());
    return ResultVOUtil.success();
  }

  /**
   * relation
   * @return
   */
  @GetMapping(path = "/relation")
  public ResultVO relation() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("enabled", EnabledEnum.relation());
    data.put("categoryType", this.dictOptionService.listByTypeForRelation(ContentTable.category));
    return ResultVOUtil.success(data);
  }

}
