package anan.base.comment.orm;

import anan.base.comment.CommentTable;
import anan.base.core.CoreTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/** Comment
 * @author anan
 * Created on 2018/8/22.
 */
@Entity(name = CommentTable.comment)
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {

  private static final long serialVersionUID = 3978596680512152259L;

  @Id
  @Column(name="id")
  private String id;

  /**
   * 故意不用对象，担心性能
   */
//  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "parent_id")
  private String parentId;

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
