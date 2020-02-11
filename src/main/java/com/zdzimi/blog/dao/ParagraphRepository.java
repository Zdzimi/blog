package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParagraphRepository extends JpaRepository <Paragraph, Integer> {

    List<Paragraph> findByArticle(Article article);

    Paragraph findByHeader(String header);
}
