package com.urundp.corona.file.dao;

import com.urundp.corona.file.model.HDirectory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/8.
 */
public interface HDirectoryRepository extends JpaRepository<HDirectory, Integer>, JpaSpecificationExecutor<HDirectory> {

  List<HDirectory> findAllByParent(HDirectory parentId);

  //TODO
  //void deleteByParent(HDirectory parentId);

  @Query(nativeQuery = true, value = "SELECT * FROM h_directory where name =? and parent_id=? ")
  List<HDirectory> findAllByNameAndAndParent(String name, Integer parentId);

  @Query(nativeQuery = true, value = "SELECT * FROM h_directory where name =? and parent_id IS NULL ")
  List<HDirectory> findAllByNameAndAndParentIsNull(String name);

  List<HDirectory> findAllByParentIsNull();

}
