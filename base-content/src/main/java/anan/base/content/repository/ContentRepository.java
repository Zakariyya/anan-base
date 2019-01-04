package anan.base.content.repository;

import anan.base.content.orm.Category;
import anan.base.content.orm.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author anan
 * Created on 2018/8/24.
 */
public interface ContentRepository  extends JpaRepository<Content, String>, JpaSpecificationExecutor<Category> {
}
