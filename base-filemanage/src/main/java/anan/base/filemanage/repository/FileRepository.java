package anan.base.filemanage.repository;

import anan.base.core.orm.DictOption;
import anan.base.filemanage.orm.File;
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
