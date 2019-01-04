package anan.base.content.orm;

import anan.base.content.ContentTable;
import anan.base.core.orm.DictOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Category pojo
 *
 * it can be a menu ,just give a categoryType of menu
 *
 * news, announcement,advertise, job ,offers,blog
 * and then the DictOption can CURD,so it can be many type
 *
 * @author anan
 * Created on 2018/8/23.
 */
@Entity(name = ContentTable.category)
@Data
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category implements Serializable {

  private static final long serialVersionUID = 1912706247667523727L;

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="parent_id")
  private Category parent;

  @JoinColumn(name="content_category_type")
  @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
  private DictOption categoryType;

  @Column(name="name")
  private String name;

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

  public Category(Integer id) {
    this.id = id;
  }

  public Category() {
  }
}
