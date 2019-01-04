package anan.base.comment.controller;

import anan.base.comment.converter.Comment2CommentDto;
import anan.base.comment.service.CommentService;
import anan.base.comment.dto.CommentDto;
import anan.base.comment.orm.Comment;
import anan.base.core.enums.ResultEnum;
import anan.base.core.exception.CoreException;
import anan.base.core.orm.ResponseResult;
import anan.base.core.util.ResultVOUtil;
import anan.base.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anan
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
   * @return ResultVO<Comment2CommentDto></>
   */
  @GetMapping("")
  public ResultVO findAll(){
    List<Comment> all = commentService.findAll();
    List<CommentDto> dtoList = new ArrayList<>();
    Comment2CommentDto c2c = new Comment2CommentDto();
    for (Comment item : all) {
      dtoList.add(c2c.convert(item));
    }
    return ResultVOUtil.success(dtoList);
  }

  /**
   * findOne
   * @param id primary key
   * @return ResultVO
   */
  @GetMapping("/{id}")
  public ResultVO findOne(@PathVariable("id") String id){
    return ResultVOUtil.success(commentService.findOne(id));
  }

  /**
   * save
   * @param data :CommentDto pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultVO save(@Valid @RequestBody Comment data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【评论管理】参数不正确, CommentDto={}", data);
      throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    commentService.save(data);
    return ResultVOUtil.success();
  }

  /**
   * update info
   * @param data :FileForm pojo
   * @return ResultVO
   */
  @PutMapping("/{id}")
  public ResultVO update(@Valid @RequestBody Comment data, @PathVariable("id") String id, BindingResult bindingResult){
    if (bindingResult.hasErrors() ){
      log.error("【评论管理】参数不正确, CommentDto={}", data);
      throw new CoreException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    ResponseResult result = new ResponseResult();
    data.setId(id);
    commentService.update(data, result);
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
    commentService.delete(id,result);
    if(result.hasMessages())
      return ResultVOUtil.error(ResultEnum.DELETE_SECTION.getCode(), result.getMessage());
    return ResultVOUtil.success();
  }




}
