package com.urundp.corona.file.dot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author yaokunyi
 * Created on 2018/8/9.
 */
@Data
public class HFileDto {

  private Integer id;
  private Integer dirId;

  private String name;//文件名称
  private String title;//文件标题
  private String description;//备注
  private String user;//用户
  private Integer size;//文件大小
  private String sizeName;//文件大小（带单位）
  private String md5;//md5校验值
  private Integer isDelete;//是否删除（0未删除，1已删除）

  @JsonIgnore
//  @Transient
  private MultipartFile mulFile;

}
