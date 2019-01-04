package anan.base.content.service;

import anan.base.content.form.ContentForm;
import anan.base.content.orm.Content;
import anan.base.core.orm.ResponseResult;

import java.util.List;

/**
 * @author anan
 * Created on 2018/8/24.
 */
public interface ContentService {

  List<Content> findAll();

  Content findOne(String id);

  Content save (ContentForm data);

  Content update (ContentForm data, ResponseResult result);

  void delete(String id, ResponseResult result);

}
