package com.anan.springboot.content.orm;

import com.anan.springboot.content.ContentTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Content pojo
 *
 * it can be a ...just give a Category
 *
 * @author anan
 * Created on 2018/8/23.
 */
@Entity(name = ContentTable.content)
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Content implements Serializable {

  private static final long serialVersionUID = -7930494651140764731L;

  @Id
  @Column(name="id")
  private String id;

  @JoinColumn(name="category_id")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private Category category;

  @Column(name="title")
  private String title;

  @Column(name="title2")
  private String title2;

  @Column(name="content")
  private String content;

  @Column(name="content2")
  private String content2;

  @Column(name="content3")
  private String content3;

  @Column(name="content4")
  private String content4;

  @Column(name="remark")
  private String remark;

  @Column(name="create_time")
  private Date createTime;

  @Column(name="update_time")
  private Date updateTime;


  public Long getCreateTime() {
    return createTime.getTime();
  }

  public Long getUpdateTime() {
    return updateTime.getTime();
  }

}
