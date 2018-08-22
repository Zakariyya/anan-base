package com.anan.sb.springboot.filemanage.orm;

import com.anan.springboot.core.CoreTable;
import com.anan.springboot.core.orm.DictOption;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * File pojo
 *
 * here use @SerializedName, property are "value" and "alternate", they are alias used to generate GSON,
 * but I dont kown why it is no working.
 *
 * > by https://www.jianshu.com/p/e740196225a4
 *
 * @author anan
 * Created on 2018/8/18.
 */
@Table(name= CoreTable.file)
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

  @Column(name="name")
  @SerializedName(value = "fileName",alternate = {"file_name","filename"})
  private String name;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @SerializedName(value = "parentId",alternate = {"parent_id"})
  @JoinColumn(name = "parent_id")
  private File parent;

  @Column(name="file_path")
  @SerializedName(value = "filePath",alternate = {"file_path"})
  private String filePath;

  /** file's size , dir is no size */
  @Column(name="size")
  private Long size;

  /** file's md5, dir is no md5 */
  @Column(name="md5")
  private String md5;

  /**
   * it's from com.anan.springboot.core.orm.DictOption.
   * if the DictOption return is null, return a exception
   */
  @JoinColumn(name="file_file_type")
//  @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private DictOption fileType;

  @Column(name="remark")
  private String remark;

  @Column(name="create_time")
  @SerializedName(value = "createTime",alternate = {"create_time"})
  private Date createTime;

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
