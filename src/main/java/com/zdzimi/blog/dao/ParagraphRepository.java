package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphRepository extends JpaRepository <Paragraph, Integer> {
}
