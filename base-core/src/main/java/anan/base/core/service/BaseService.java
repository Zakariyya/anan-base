package anan.base.core.service;

import anan.base.core.orm.ResponseResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anan
 * Created on 2019/01/23.
 */
public interface BaseService {

  ResponseResult delete(String id, JpaRepository t, ResponseResult result);

}
