package com.anan.sb.springboot.filemanage.repository;

import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.springboot.core.orm.DictOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author anan
 * Created by anan on 2018/8/18.
 */
public interface FileRepository  extends JpaRepository<File, Integer>, JpaSpecificationExecutor<DictOption> {

  List<File> findAllByParent(File parent);

}
