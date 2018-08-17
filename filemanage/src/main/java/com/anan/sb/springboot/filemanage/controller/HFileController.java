package com.urundp.corona.file.controller;

import com.urundp.corona.common.utils.CommonFunctions;
import com.urundp.corona.common.utils.DataResult;
import com.urundp.corona.common.utils.DataResultOfPage;
import com.urundp.corona.file.config.Config;
import com.urundp.corona.file.dao.HFileRepository;
import com.urundp.corona.file.dot.HFileDto;
import com.urundp.corona.file.enums.GlobalEnum;
import com.urundp.corona.file.model.HDirectory;
import com.urundp.corona.file.model.HFile;
import com.urundp.corona.file.service.HFileService;
import com.urundp.corona.file.util.FileUtil;
import com.urundp.corona.file.util.HdfsUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import static com.urundp.corona.file.util.HdfsUtils.*;

/**
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@RestController
@RequestMapping("/hfile")
@Slf4j
public class HFileController {

  private final HFileService hFileService;
  private final Config config;
  private final HFileRepository hFileRepository;

  @Autowired
  public HFileController(HFileService hFileService, Config config, HFileRepository hFileRepository) {
    this.hFileService = hFileService;
    this.config = config;
    this.hFileRepository = hFileRepository;
  }

  /**
   * 列表页(分页)
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
    Page<HFile> all = hFileService.findAll(request);
    return CommonFunctions.result(true, all.getContent(), GlobalEnum.SUCCESS.getMessage(), null,all.getNumberOfElements());

  }

  /**
   * 返回所有列表
   * @return 返回结果集
   */
  @RequestMapping(value = "findAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> findAll(){

    String uploadPath = "d:/";
//    File file = new File(uploadPath + "ps.exe");
    try {
//      uploadFile(getConf(),file.getPath());
      HdfsUtils.downloadFile(getConf(), "http://192.168.100.214:50070/uploadfile/hhh.txt", "D:/file");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return CommonFunctions.result(true, hFileService.findAll(), GlobalEnum.SUCCESS.getMessage(), null);
  }

  /**
   * 获取单个
   * @param id 主键
   * @return 返回结果集
   */
  @RequestMapping(value = "findOne", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> findOne(@RequestParam(value = "id") Integer id){
    val data = hFileService.findOne(id);
    return CommonFunctions.result(true, data, GlobalEnum.SUCCESS.getMessage(), null);

  }

  /**
   * 更新
   *
   * @param dto:HFile pojo
   * @return 返回结果集
   */
  @RequestMapping(value = "save", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> save(@RequestBody HFileDto dto){
    HFile data = new HFile();
    if (null == dto.getId()) {
      data.setUploadTime(new Timestamp(System.currentTimeMillis()));
    }else{
      data = hFileRepository.getOne(dto.getId());
    }
    BeanUtils.copyProperties(dto,data);
    val save = hFileService.save(data);
    return CommonFunctions.result(true, save, GlobalEnum.SUCCESS.getMessage(), null);

  }


  /**
   * 删除/批量删除
   * @param params {"id":"1,2,3,4,5"}
   * @return 返回结果集
   */
  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public DataResult<Object> batchApply(@RequestBody Map<String, String> params) {
    hFileService.delete(params.get("id"));
    return CommonFunctions.result(true, null, GlobalEnum.SUCCESS.getMessage(), null);
  }


  /**
   * 记录一条记录，另外调用Hdfs命令放入文件系统
   * 文档标题、文档描述、上传时间、文档数据标签
   * @param dto : HFileDto
   * @return 返回结果集
   */
  @ResponseBody
  @RequestMapping(value = "/io", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public DataResult<Object> upload(@RequestBody HFileDto dto){
    HFile data = new HFile();
    data.setUploadTime(new Timestamp(System.currentTimeMillis()));
    data.setHDirectory(new HDirectory(dto.getDirId()));
    data.setDescription(dto.getDescription());

    try {
      File file = FileUtil.uploadFile(dto.getMulFile(), config.getUploadPath());
      uploadFile(getConf(),file.getPath());

      data.setMd5(FileUtil.getMd5ByFile(file));
      data.setSize(file.length());
      data.setSizeName( FileUtil.getFileLength(file.length()));
      data.setFilePath(file.getPath());
      data.setName(file.getName());

      data = hFileService.save(data);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.info("==== 文件上传出现异常：FileNotFoundException：：位置：HFileController.upload/POST  ===");
      return CommonFunctions.result(false, null, GlobalEnum.FAIL.getMessage(), null);
    } catch (Exception e) {
      e.printStackTrace();
      log.info("==== 文件上传出现异常：Exception：HDFS上传出现异常：位置：HFileController.upload/POST  ===");
      return CommonFunctions.result(false, null, GlobalEnum.FAIL.getMessage(), null);
    }

    return CommonFunctions.result(false, data, GlobalEnum.SUCCESS.getMessage(), null);
  }

  /**
   * 提供文件下载
   * @return 返回结果集
   */
  @RequestMapping(value = "/io", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResponseEntity<InputStreamResource> download(@RequestParam(value = "id") Integer id) throws Exception {

    val one = hFileService.findOne(id);
//    val filePath = one.getFilePath();

//    HdfsUtils.downloadFile(getConf(), "http://192.168.100.214:50070/uploadfile/hhh.txt", "D:/file");
    val filePath = "D:/file/haha1534299140658.csv";

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
      log.info("==== 文件下载出现异常：IOException：：位置：HFileController.download/GET  ===");
    }
    return null;
  }



}
