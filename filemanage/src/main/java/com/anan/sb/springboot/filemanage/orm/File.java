package com.anan.sb.springboot.filemanage.orm;

import com.anan.sb.springboot.filemanage.orm.core.DictOption;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 文件
 *
 * 这里使用了@SerializedName注解，属性是value和alternate，目的是起别名用来生成gson的。
 * > 具体看 https://www.jianshu.com/p/e740196225a4
 *
 * @author anan
 * Created on 2018/8/18.
 */
@Table(name="file_file")
@Entity
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class File implements Serializable {
  private static final long serialVersionUID = 9114120134178532287L;

  @Id
  @Column(name="id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  /** 文件名称  */
  @Column(name="name")
  @SerializedName(value = "fileName",alternate = {"file_name","filename"})
  private String name;

  /** 父节点 */
  @ManyToOne(cascade = CascadeType.REFRESH)
  @SerializedName(value = "parentId",alternate = {"parent_id"})
  @JoinColumn(name = "parent_id")
  private File parent;

  /** 文件在服务器路径 */
  @Column(name="file_path")
  @SerializedName(value = "filePath",alternate = {"file_path"})
  private String filePath;

  /** 文件大小 */
  @Column(name="size")
  private Long size;

  /** 文件md5 */
  @Column(name="md5")
  private String md5;

  /** 文件类型 读取字典表，如果字典表中没有返回异常 */
  @JoinColumn(name="file_file_type")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
  @SerializedName(value = "fileType",alternate = {"file_type"})
  private DictOption fileType;

  /** 备注 */
  @Column(name="remark")
  private String remark;

  /** 创建时间 */
  @Column(name="create_time")
  @SerializedName(value = "createTime",alternate = {"create_time"})
  private Date createTime;

  /** 更新时间 */
  @Column(name="update_time")
  @SerializedName(value = "updateTime",alternate = {"update_time"})
  private Date updateTime;

  @JsonIgnore
  @Transient
  private MultipartFile mulFile;


  public Long getCreateTime() {
    return createTime.getTime();
  }

  public Long getUpdateTime() {
    return updateTime.getTime();
  }

  public File() {
  }

  public File(Integer id) {
    this.id = id;
  }
}
