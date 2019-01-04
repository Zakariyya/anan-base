package anan.base.comment.repository;

import anan.base.comment.CommentTable;
import anan.base.comment.orm.Comment;
import anan.base.core.CoreTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author anan
 * Created on 2018/8/22.
 */
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

  @Modifying
  @Query(value = "UPDATE "+ CommentTable.comment +" SET content =? WHERE id = ?", nativeQuery = true)
  Integer updateContentById(String content, String id);


}
