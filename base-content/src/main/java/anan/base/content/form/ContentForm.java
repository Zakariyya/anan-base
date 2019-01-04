package anan.base.content.form;

import anan.base.core.form.CoreForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author anan
 * Created on 2018/8/24.
 */
@Data
public class ContentForm extends CoreForm<String> {

  private String id;

  @NotNull(message = "类别必填")
  private Integer categoryId;

  @NotNull(message = "标题")
  private String title;

  private String title2;

  private String content;

  private String content2;

  private String content3;

  private String content4;

  private String remark;


}

