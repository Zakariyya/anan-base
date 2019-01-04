package anan.base.filemanage.form;

import anan.base.core.form.CoreForm;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author anan
 * Create on 2018/8/19
 */
@Data
public class FileForm extends CoreForm<Integer> {

  //不做参数传递
  private Integer id;

  @NotBlank(message = "名称必填")
  private String name;//文件,文件名称

  private Integer parentId;

  @NotNull(message = "类型必填")
  private Integer fileTypeId;

  private String remark;

  private MultipartFile mulFile;

  private String md5;

  private long size;

  private String filePath;

}
