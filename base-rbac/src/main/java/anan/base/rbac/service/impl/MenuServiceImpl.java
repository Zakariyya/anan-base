package anan.base.rbac.service.impl;

import anan.base.core.orm.ResponseResult;
import anan.base.core.service.BaseService;
import anan.base.rbac.orm.Menu;
import anan.base.rbac.repository.MenuRepository;
import anan.base.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author anan
 * @created by anan on 2019/2/21 15:40
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

  @Autowired
  private MenuRepository repository;

  @Autowired
  private BaseService baseService;

  @Override
  public Page<Menu> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<Menu> findAll() {
    return repository.findAll();
  }

  @Override
  public Menu findOne(Integer id) {
    return repository.findById(id).get();
  }

  @Override
  public Menu save(Menu data) {
    return repository.save(data);
  }

  @Override
  public Menu update(Menu data) {
    return repository.save(data);
  }

  @Override
  public ResponseResult delete(String id, ResponseResult result) {
    return baseService.delete(id,repository,result);
  }


}
