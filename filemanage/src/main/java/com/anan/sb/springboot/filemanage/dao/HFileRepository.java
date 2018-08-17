package com.urundp.corona.file.dao;

import com.urundp.corona.file.model.HDirectory;
import com.urundp.corona.file.model.HFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author yaokunyi
 * Created by yaokunyi on 2018/8/8.
 */
public interface HFileRepository  extends JpaRepository<HFile, Integer>, JpaSpecificationExecutor<HFile> {

  List<HFile> findAllByHDirectory(HDirectory dirId);

}
