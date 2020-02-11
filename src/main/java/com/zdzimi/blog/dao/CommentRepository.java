package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Integer> {

    List<Comment> findByArticle(Article article);
}
