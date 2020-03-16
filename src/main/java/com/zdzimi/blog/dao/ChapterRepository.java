package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository <Chapter, Integer> {

    Optional<Chapter> findByChapterTitle(String chapterTitle);
}
