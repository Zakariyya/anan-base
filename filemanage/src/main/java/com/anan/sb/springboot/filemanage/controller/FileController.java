package com.anan.sb.springboot.filemanage.controller;

import com.anan.sb.springboot.filemanage.config.FileConfig;
import com.anan.sb.springboot.filemanage.enums.ResultEnum;
import com.anan.sb.springboot.filemanage.exception.FileException;
import com.anan.sb.springboot.filemanage.form.FileForm;
import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.service.FileService;
import com.anan.sb.springboot.filemanage.util.FileUtil;
import com.anan.springboot.core.orm.ResponseResult;
import com.anan.springboot.core.util.ResultVOUtil;
import com.anan.springboot.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author anan
 * Created on 2018/8/8.
 */
@RestController
@RequestMapping("/filemanager")
@Slf4j
public class FileController {

  @Autowired
  private FileService fileService;

  @Autowired
  private FileConfig fileConfig;

  /**
   * findAll
   * @return ResultVO
   */
  @GetMapping("/file")
  public ResultVO findAll(){
    List<File> all = fileService.findAll();
    return ResultVOUtil.success(all);
  }

  /**
   * findOne
   * @param id primary key
   * @return ResultVO
   */
  @GetMapping("/file/{id}")
  public ResultVO findOne(@PathVariable("id") Integer id){
    return ResultVOUtil.success(fileService.findOne(id));
  }

  /**
   * save
   * @param data :FileForm pojo
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping(value = "/file",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultVO save(@Valid @RequestBody FileForm data, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      log.error("【文件管理】参数不正确, FileForm={}", data);
      throw new FileException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    fileService.save(data);
    return ResultVOUtil.success();

  }


  /**
   * update file info
   * @param data :FileForm pojo
   * @return ResultVO
   */
  @PutMapping("/file/{id}")
  public ResultVO update(@Valid @RequestBody FileForm data, @PathVariable("id") Integer id, BindingResult bindingResult){
    if (bindingResult.hasErrors() || null == data.getFileTypeId()) {
      log.error("【文件管理】参数不正确, FileForm={}", data);
      throw new FileException(ResultEnum.PARAM_ERROR.getCode(),
              bindingResult.getFieldError().getDefaultMessage());
    }
    ResponseResult result = new ResponseResult();
    data.setId(id);
    fileService.update(data, result);
    if(result.hasErrors())
      return ResultVOUtil.error(ResultEnum.FAILURE.getCode(), result.getMessage());
    return ResultVOUtil.success();

  }


  /**
   * batch delete
   * @param id // @RequestBody Map<String,String> param {"id":"1,2,3,4,5"}
   *           // @PathVariable String id :"1,2,3,4,5", force: 1/true/0/false
   * @return ResultVO
   */
  @ResponseBody
  @RequestMapping(value = "/file/{id}/{force}", method = RequestMethod.DELETE)
  public ResultVO delete(@PathVariable("id") String id, @PathVariable("force") boolean force) {
    ResponseResult result = new ResponseResult();
    fileService.delete(id, force,result);
    if(result.hasMessages())
      return ResultVOUtil.error(ResultEnum.FILE_DELETE_SECTION.getCode(), result.getMessage());
    return ResultVOUtil.success();
  }


  /**
   * upload file
   * @param data : FileForm
   * @return ResultVO
   */
  @ResponseBody
  @PostMapping(value = "/file/io",produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResultVO upload(@RequestBody FileForm data){
    try {
      java.io.File file = FileUtil.uploadFile(data.getMulFile(), fileConfig.getUploadPath());

      data.setMd5(FileUtil.getMd5ByFile(file));
      data.setSize(file.length());
      data.setFilePath(file.getPath());
      data.setName(file.getName());

      val save  = fileService.save(data);
      return ResultVOUtil.success();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.info("【文件管理】文件上传出现异常：FileNotFoundException：：位置：HFileController.upload/POST ");
      return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_EXCEPTION.getCode(), ResultEnum.FILE_UPLOAD_EXCEPTION.getMessage());
    }
  }

  /**
   * download file
   * @return ResultVO
   */
  @RequestMapping(value = "/file/io/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
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
