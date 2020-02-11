package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository <Article, Integer> {

    Article findByArticleTitle(String articleTitle);

    List<Article> findByChapter(Chapter chapter);

}
