package com.anan.sb.springboot.filemanage.controller;

import com.anan.sb.springboot.filemanage.config.FileConfig;
import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.service.FileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urundp.corona.file.util.FileUtil;
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
import org.springframework.web.bind.annotation.*;

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
  @GetMapping("/findAll")
  public String findAll(){
    List<File> all = fileService.findAll();
    Gson gson = new Gson();
    String jsonObject = gson.toJson(all);
    log.info("========" + jsonObject);

//    Gson G = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    Gson G = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
    String aaa = G.toJson(all);
    log.info(":::::::"+aaa);
    return jsonObject;
  }

//  /**
//   * 获取单个
//   * @param id 主键
//   * @return 返回结果集
//   */
//  @RequestMapping(value = "findOne", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
//  public DataResult<Object> findOne(@RequestParam(value = "id") Integer id){
//    val data = fileService.findOne(id);
//    return CommonFunctions.result(true, data, GlobalEnum.SUCCESS.getMessage(), null);
//
//  }
//
//  /**
//   * 更新
//   *
//   * @param dto:File pojo
//   * @return 返回结果集
//   */
//  @RequestMapping(value = "save", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
//  public DataResult<Object> save(@RequestBody HFileDto dto){
//    File data = new File();
//    if (null == dto.getId()) {
//      data.setUploadTime(new Timestamp(System.currentTimeMillis()));
//    }else{
//      data = hFileRepository.getOne(dto.getId());
//    }
//    BeanUtils.copyProperties(dto,data);
//    val save = fileService.save(data);
//    return CommonFunctions.result(true, save, GlobalEnum.SUCCESS.getMessage(), null);
//
//  }
//
//
//  /**
//   * 删除/批量删除
//   * @param params {"id":"1,2,3,4,5"}
//   * @return 返回结果集
//   */
//  @ResponseBody
//  @RequestMapping(value = "/delete", method = RequestMethod.POST)
//  public DataResult<Object> batchApply(@RequestBody Map<String, String> params) {
//    fileService.delete(params.get("id"));
//    return CommonFunctions.result(true, null, GlobalEnum.SUCCESS.getMessage(), null);
//  }
//
//
//  /**
//   * 记录一条记录，另外调用Hdfs命令放入文件系统
//   * 文档标题、文档描述、上传时间、文档数据标签
//   * @param dto : HFileDto
//   * @return 返回结果集
//   */
//  @ResponseBody
//  @RequestMapping(value = "/io", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
//  public DataResult<Object> upload(@RequestBody File dto){
//    File data = new File();
//    data.setUploadTime(new Timestamp(System.currentTimeMillis()));
//    data.setHDirectory(new HDirectory(dto.getDirId()));
//    data.setDescription(dto.getDescription());
//
//    try {
//      java.io.File file = FileUtil.uploadFile(dto.getMulFile(), fileConfig.getUploadPath());
//      uploadFile(getConf(),file.getPath());
//
//      data.setMd5(FileUtil.getMd5ByFile(file));
//      data.setSize(file.length());
//      data.setSizeName( FileUtil.getFileLength(file.length()));
//      data.setFilePath(file.getPath());
//      data.setName(file.getName());
//
//      data = fileService.save(data);
//
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//      log.info("==== 文件上传出现异常：FileNotFoundException：：位置：HFileController.upload/POST  ===");
//      return CommonFunctions.result(false, null, GlobalEnum.FAIL.getMessage(), null);
//    } catch (Exception e) {
//      e.printStackTrace();
//      log.info("==== 文件上传出现异常：Exception：HDFS上传出现异常：位置：HFileController.upload/POST  ===");
//      return CommonFunctions.result(false, null, GlobalEnum.FAIL.getMessage(), null);
//    }
//
//    return CommonFunctions.result(false, data, GlobalEnum.SUCCESS.getMessage(), null);
//  }
//
//  /**
//   * 提供文件下载
//   * @return 返回结果集
//   */
//  @RequestMapping(value = "/io", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
//  public ResponseEntity<InputStreamResource> download(@RequestParam(value = "id") Integer id) throws Exception {
//
//    val one = fileService.findOne(id);
////    val filePath = one.getFilePath();
//
////    HdfsUtils.downloadFile(getConf(), "http://192.168.100.214:50070/uploadfile/hhh.txt", "D:/file");
//    val filePath = "D:/file/haha1534299140658.csv";
//
//    val file = new FileSystemResource(filePath);
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//    headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
//    headers.add("Pragma", "no-cache");
//    headers.add("Expires", "0");
//
//    try {
//      return ResponseEntity
//              .ok()
//              .headers(headers)
//              .contentLength(file.contentLength())
//              .contentType(MediaType.parseMediaType("application/octet-stream"))
//              .body(new InputStreamResource(file.getInputStream()));
//    } catch (IOException e) {
//      e.printStackTrace();
//      log.info("==== 文件下载出现异常：IOException：：位置：HFileController.download/GET  ===");
//    }
//    return null;
//  }
//


}
