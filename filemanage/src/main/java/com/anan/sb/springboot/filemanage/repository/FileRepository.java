package com.anan.sb.springboot.filemanage.repository;

import com.anan.sb.springboot.filemanage.orm.File;
import com.anan.sb.springboot.filemanage.orm.core.DictOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author anan
 * Created by anan on 2018/8/18.
 */
public interface FileRepository  extends JpaRepository<File, Integer>, JpaSpecificationExecutor<DictOption> {


}
