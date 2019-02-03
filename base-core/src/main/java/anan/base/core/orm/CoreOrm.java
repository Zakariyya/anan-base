package anan.base.core.orm;

import java.util.Date;

/**
 *
 * @author anan
 * @created 2019/1/31 0:16
 *
 * Spring boot 2.x不再默认将 java.util.Date 序列化为 Timestamp
 * 如果需要序列化为时间戳，在application.yaml配置文件中添加时间格式化配置即可。
 *
 *   jackson:
 *     serialization:
 *       WRITE_DATES_AS_TIMESTAMPS: true
 *
 */

public class CoreOrm {

  @Deprecated
  private Date createTime;
  @Deprecated
  private Date updateTime;

  @Deprecated
  public Long getCreateTime() {
    return createTime.getTime();
  }

  @Deprecated
  public Long getUpdateTime() {
    return updateTime.getTime();
  }
}
