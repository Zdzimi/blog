package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository <Chapter, Integer> {

    Chapter findByChapterTitle(String chapterTitle);
}
