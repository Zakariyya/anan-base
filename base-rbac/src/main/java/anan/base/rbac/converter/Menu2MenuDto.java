package anan.base.rbac.converter;

import anan.base.core.converter.CoreConverter;
import anan.base.rbac.dto.MenuDto;
import anan.base.rbac.orm.Menu;
import anan.base.rbac.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author anan
 * @created by anan on 2019/2/22 13:23
 */
@Component
public class Menu2MenuDto extends CoreConverter<Menu, MenuDto> {

  @Autowired
  protected MenuService service;
  private static Menu2MenuDto  m2m ;

  @Override
  @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
  public void init() {
    m2m = this;
    m2m.service = this.service;
    // 初使化时将已静态化的testService实例化
  }


  @Override
  public MenuDto set(Menu data, MenuDto dto) {
    BeanUtils.copyProperties(data,dto);
    if(data.getParentId() != null)dto.setParentId(data.getParentId().getId());
    return dto;
  }
}
