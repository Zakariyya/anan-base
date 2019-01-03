package anan.base.content.form;

import anan.base.core.form.CoreForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author anan
 * Created on 2018/8/24.
 */
@Data
public class CategoryForm extends CoreForm<Integer> {

  private Integer id;

  private Integer parentId;

  @NotNull(message = "类型必填")
  private Integer categoryId;

  @NotNull(message = "名称必填")
  private String name;

  private String remark;


}
