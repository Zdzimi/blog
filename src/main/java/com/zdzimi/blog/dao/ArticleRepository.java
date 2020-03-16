package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository <Article, Integer> {

    Optional<Article> findByArticleTitle(String articleTitle);
}
