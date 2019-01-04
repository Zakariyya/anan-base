package anan.base.core.orm;

import anan.base.core.CoreTable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author anan
 * Create on 2018/8/19
 */
@Entity(name = CoreTable.dictOption)
@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DictOption implements Serializable, Comparable<DictOption>  {

  private static final long serialVersionUID = 8632165618570176042L;

  @Id
  @Column(name="id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private  Integer  id;
  /**
   * 字典类型
   */
  @Column(name="dict_type")
  private String dictType;

  /**
   * 选项值
   */
  @Column(name="option_value")
  private String optionValue;

  /**
   * 选项文本
   */
  @Column(name="label")
  private String label;

  /**
   * 扩展字段a
   */
  @Column(name="k1")
  private String k1;

  /**
   * 扩展字段b
   */
  @Column(name="k2")
  private String k2;

  /**
   * 选项备注
   */
  @Column(name="option_remark")
  private String optionRemark;

  /**
   * 排序
   */
  @Column(name="show_order")
  private Integer  showOrder;

  /**
   * 是否可编辑(含删除)
   */
  @Column(name="editable")
  private Integer  editable;


  @Override
  public int compareTo(DictOption o) {
    return Integer.compare(this.id, o.id);
  }
}
