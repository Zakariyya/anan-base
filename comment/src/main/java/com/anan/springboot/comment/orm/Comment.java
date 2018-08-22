package com.anan.springboot.comment.orm;

import com.anan.springboot.core.CoreTable;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/** Comment
 * @author yaokunyi
 * Created on 2018/8/22.
 */
@Entity(name = CoreTable.comment)
@Data
@DynamicUpdate
public class Comment implements Serializable {

  private static final long serialVersionUID = 3978596680512152259L;

  @Id
  @Column(name="id")
  private String id;

  /**
   * 故意不用对象
   */
//  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "parent_id")
  private String parentId;

  @NotNull
  @Column(name="content")
  private String content;

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


  public Comment(String uuid) {
    this.id = uuid;
  }

  public Comment() {
  }
}
