package com.urundp.corona.file.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 文件
 * @author yaokunyi
 * Created on 2018/8/8.
 */
@Table(name="h_file")
@Entity
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HFile implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  /**
   * 业务分类id
   */
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name="category_id")
  @JsonIgnore
  private BusinessCategory businessCategory;


  /**
   * 目录ID
   */
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "dir_id")
  private HDirectory hDirectory;

  /**
   * 文件名称
   */
  @Column(name="name")
  private String name;

  /**
   * 标题
   */
  @Column(name="title")
  private String title;

  /**
   * 备注
   */
  @Column(name="description")
  private String description;

  /**
   * 上传时间
   */
  @Column(name="upload_time")
  private Timestamp uploadTime;

  /**
   * 上传用户(保留)
   */
  @Column(name="user")
  private String user;

  /**
   * 文件在服务器路径
   */
  @Column(name="file_path")
  private String filePath;

  /**
   * HDFS路径
   */
  @Column(name="hdfs_path")
  private String hdfsPath;

  /**
   * 文件大小
   */
  @Column(name="size")
  private Long size;

  /**
   * 文件大小(带单位)
   */
  @Column(name="size_name")
  private String sizeName;

  /**
   * 文件md5
   */
  @Column(name="md5")
  private String md5;

  /**
   * 是否删除 0:未删除 1:已删除
   * 枚举
   */
  @Column(name="is_delete")
  private Integer isDelete;


  public Long getUploadTime() {
    return uploadTime.getTime();
  }


}
