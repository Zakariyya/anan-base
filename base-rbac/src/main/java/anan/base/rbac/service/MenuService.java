package anan.base.rbac.service;

import anan.base.core.orm.ResponseResult;
import anan.base.rbac.orm.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:38
 */
public interface MenuService {


  Page<Menu> findAll(Pageable pageable);

  List<Menu> findAll();

  Menu findOne(Integer id);

  Menu save(Menu data);

  Menu update(Menu data) ;

  ResponseResult delete(String id, ResponseResult result);


}
