package com.urundp.corona.file.dot;

import lombok.Data;

import java.util.List;

/**
 * @author yaokunyi
 * Created on 2018/8/9.
 */
@Data
public class HDirectoryDto {

  private Integer id;
  private Integer parentId;
  private String name;
  //  private Integer quantity;
  private Integer categoryId;


  //该目录下的目录
  private List<HDirectoryDto> dirList;
  //该目录下的文件
  private List<HFileDto> fileList;


}
