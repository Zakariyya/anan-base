package anan.base.core.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * @author anan
 * Create on 2018/8/18
 */
@Getter
@Setter
public class ResultVO<T> implements Serializable {

  private static final long serialVersionUID = -1254223930581160279L;
  /** 状态码. */
  private Integer code;
  /** 提示信息. */
  private String msg;
  /** 具体内容. */
  private T data;

  @Override
  public String toString() {
    return "ResultVO{" +
              "code=" + code +
              ", data='" + data + '\'' +
              ", msg=" + msg +
            '}';
  }
}
