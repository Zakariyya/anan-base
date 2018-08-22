package com.anan.springboot.comment.controller;

import com.anan.sb.springboot.filemanage.enums.ResultEnum;
import com.anan.springboot.comment.converter.Comment2CommentDto;
import com.anan.springboot.comment.dto.CommentDto;
import com.anan.springboot.comment.exception.CommentException;
import com.anan.springboot.comment.orm.Comment;
import com.anan.springboot.comment.service.CommentService;
import com.anan.springboot.core.orm.ResponseResult;
import com.anan.springboot.core.util.ResultVOUtil;
import com.anan.springboot.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

  @Autowired
  private CommentService commentService;


  /**
   * findAll
   * @return ResultVO
   */
  @GetMapping("/comment")
  public ResultVO findAll(){
    List<Comment> all = commentService.findAll();
    return ResultVOUtil.success(all);
  }

  /**
   * findOne
   * @param id primary key
   * @return ResultVO
   */
  @GetMapping("/comment/{id}")
  public ResultVO findOne(@PathVariable("id") String id){
    return ResultVOUtil.success(commentService.findOne(id));
  }

  /**
   * save
   * @param data :CommentDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping(value = "/file",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultVO save(@Valid @RequestBody CommentDto data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【评论管理】参数不正确, CommentDto={}", data);
      throw new CommentException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    commentService.save(data);
    return ResultVOUtil.success();
  }

  /**
   * update file info
   * @param data :FileForm pojo
   * @return ResultVO
   */
  @PutMapping("/file/{id}")
  public ResultVO update(@Valid @RequestBody CommentDto data, @PathVariable("id") String id, BindingResult bindingResult){
    if (bindingResult.hasErrors() ){
      log.error("【文件管理】参数不正确, FileForm={}", data);
      throw new CommentException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    ResponseResult result = new ResponseResult();
    data.setId(id);

    Comment2CommentDto c2c = new Comment2CommentDto();

    

    commentService.update(data, result);
    if(result.hasErrors())
      return ResultVOUtil.error(ResultEnum.FAILURE.getCode(), result.getMessage());
    return ResultVOUtil.success();

  }


}
