package com.anan.sb.springboot.filemanage.service.impl;

import com.anan.sb.springboot.filemanage.form.FileForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileServiceImplTest {

  @Autowired
  private FileServiceImpl fileService;


  public FileForm make(){
    FileForm data = new FileForm();

    data.setName("aaaaaaas");
    data.setFileTypeId(102);

    return data;
  }

  @Test
  public void save() {
    FileForm make = make();
    log.info("======"+fileService.save(make));

  }
}