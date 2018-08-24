package com.anan.springboot.content.repository;

import com.anan.springboot.content.orm.Category;
import com.anan.springboot.content.orm.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */
public interface ContentRepository  extends JpaRepository<Content, String>, JpaSpecificationExecutor<Category> {
}
