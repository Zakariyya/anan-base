package com.anan.sb.springboot.filemanage.controller;

import com.anan.sb.springboot.filemanage.config.FileConfig;
import com.anan.sb.springboot.filemanage.enums.ResultEnum;
import com.anan.sb.springboot.filemanage.exception.FileException;
import com.anan.sb.springboot.filemanage.form.FileForm;
import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.orm.core.ResponseResult;
import com.anan.sb.springboot.filemanage.service.FileService;
import com.anan.sb.springboot.filemanage.util.FileUtil;
import com.anan.sb.springboot.filemanage.util.ResultVOUtil;
import com.anan.sb.springboot.filemanage.vo.ResultVO;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;
/**
 * @author anan
 * Created on 2018/8/8.
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

  @Autowired
  private FileService fileService;

  @Autowired
  private FileConfig fileConfig;

  /**
   * 返回所有列表
   * @return 返回结果集
   */
  @GetMapping("/find")
  public ResultVO findAll(){
    List<File> all = fileService.findAll();
    return ResultVOUtil.success(all);
  }

  /**
   * 获取单个
   * @param id 主键
   * @return 返回结果集
   */
  @GetMapping("/find/{id}")
  public ResultVO findOne(@PathVariable("id") Integer id){
    return ResultVOUtil.success(fileService.findOne(id));
  }

  /**
   * 保存文件夹
   * @param data :File pojo
   * @return 返回结果集
   */
  @ResponseBody
  @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultVO save(@Valid @RequestBody FileForm data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【文件管理】参数不正确, FileForm={}", data);
      throw new FileException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    val save = fileService.save(data);
    return ResultVOUtil.success(save);

  }


  /**
   * 更新文件夹、文件基本信息
   * @param data :File pojo
   * @return 返回结果集
   */
  @PutMapping("/save/{id}")
  public ResultVO update(@Valid @RequestBody FileForm data, @PathVariable("id") Integer id, BindingResult bindingResult){
    if (bindingResult.hasErrors() || null != data.getFileTypeId()) {
      log.error("【文件管理】参数不正确, FileForm={}", data);
      throw new FileException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    ResponseResult result = new ResponseResult();
    data.setId(id);
    val save = fileService.update(data, result);
    if(result.hasErrors())
      return ResultVOUtil.error(ResultEnum.FAILURE.getCode(), result.getMessage());
    return ResultVOUtil.success(save);

  }



  /**
   * 删除/批量删除
   * @param id // @RequestBody Map<String,String> param {"id":"1,2,3,4,5"}
   *           // @PathVariable String id :"1,2,3,4,5"
   * @return 返回结果集
   */
  @ResponseBody
  @RequestMapping(value = "/delete/{id}/{force}", method = RequestMethod.POST)
  public ResultVO batchApply(@PathVariable String id, @PathVariable(required = false) boolean force) {
    ResponseResult result = new ResponseResult();
    fileService.delete(id, force,result);
    if(result.hasMessages())
      return ResultVOUtil.error(ResultEnum.FILE_DELETE_SECTION.getCode(), result.getMessage());
    return ResultVOUtil.success();
  }


  /**
   * @param data : File
   * @return 返回结果集
   */
  @ResponseBody
  @RequestMapping(value = "/io", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResultVO upload(@RequestBody FileForm data){
    try {
      java.io.File file = FileUtil.uploadFile(data.getMulFile(), fileConfig.getUploadPath());

      data.setMd5(FileUtil.getMd5ByFile(file));
      data.setSize(file.length());
      data.setFilePath(file.getPath());
      data.setName(file.getName());

      val save  = fileService.save(data);
      return ResultVOUtil.success(save);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.info("【文件管理】文件上传出现异常：FileNotFoundException：：位置：HFileController.upload/POST ");
      return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_EXCEPTION.getCode(), ResultEnum.FILE_UPLOAD_EXCEPTION.getMessage());
    }
  }

  /**
   * 提供文件下载
   * @return 返回结果集
   */
  @RequestMapping(value = "/io/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResponseEntity<InputStreamResource> download(@PathVariable("id") Integer id) throws Exception {

    val filePath =  fileService.findOne(id).getFilePath();
    val file = new FileSystemResource(filePath);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");

    try {
      return ResponseEntity
              .ok()
              .headers(headers)
              .contentLength(file.contentLength())
              .contentType(MediaType.parseMediaType("application/octet-stream"))
              .body(new InputStreamResource(file.getInputStream()));
    } catch (IOException e) {
      e.printStackTrace();
      log.info("【文件管理】文件下载出现异常：IOException：：位置：HFileController.download/GET ");
    }
    return null;
  }



}
