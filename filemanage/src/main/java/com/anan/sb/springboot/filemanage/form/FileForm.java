package com.anan.sb.springboot.filemanage.form;

import com.anan.sb.springboot.filemanage.orm.File;
import lombok.Data;

/**
 * @author anan
 * Create on 2018/8/19
 */
@Data
public class FileForm extends File {

  private Integer id;

  private String name;//文件夹名称
  private Integer parentId;
  private Integer fileTypeId;
  private String remark;

}
