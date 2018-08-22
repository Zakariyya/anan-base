package com.anan.springboot.comment.repository;

import com.anan.springboot.comment.orm.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yaokunyi
 * Created on 2018/8/22.
 */
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

}
