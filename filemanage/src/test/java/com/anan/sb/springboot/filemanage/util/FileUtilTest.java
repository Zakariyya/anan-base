package com.anan.sb.springboot.filemanage.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
@Slf4j
public class FileUtilTest {


  /**
   * make a MulFile
   */
  public MultipartFile makeMulFile(){
    String path = "d:/";
    File file = new File(path + "源文件.md");
    try {
      MultipartFile mulFile = new MockMultipartFile(
              "文件名.md",        //文件名
              "originalName文件名.md",        //originalName 相当于上传文件在客户机上的文件名
              ContentType.APPLICATION_OCTET_STREAM.toString(),    //文件类型
              new FileInputStream(file)                           //文件流
      );
      return mulFile;
    } catch (IOException e) {
      e.printStackTrace();
      log.info("创建文件流时出现了异常");
    }
    return null;
  }


  /*************************************************
   *******                                  ********
   *******    upload / download by server   ********
   *******                                  ********
   *************************************************/

  @Test
  public void uploadFile2Server() {
    String uploadPath = "d:/FileUtilTest/upload/";
    File file = FileUtil.uploadFile(makeMulFile(), uploadPath);
    log.info("uploadFile path:"+file.getPath()+"/"+file.getName());
    log.info("uploadFile end");
  }

  @Test
  public void getMd5ByFile() {
    File file = new File("d:/源文件.md");
    try {
      log.info(file.getName()+"文件MD5的值::"+FileUtil.getMd5ByFile(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      log.info("文件找不到异常");
    }
  }

  @Test
  public void getFileLength() {
    File file = new File("d:/源文件.md");
    log.info(file.getName()+"文件的大小是：："+FileUtil.getFileLength(file.length()));
  }


}