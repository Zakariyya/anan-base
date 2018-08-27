package com.anan.springboot.comment.repository;

import com.anan.springboot.comment.CommentTable;
import com.anan.springboot.comment.orm.Comment;
import com.anan.springboot.core.CoreTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

  @Modifying
  @Query(value = "UPDATE "+ CommentTable.comment +" SET content =? WHERE id = ?", nativeQuery = true)
  Integer updateContentById(String content, String id);


}
