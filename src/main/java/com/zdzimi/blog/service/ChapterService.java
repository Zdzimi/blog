package com.zdzimi.blog.service;

import com.zdzimi.blog.dao.ChapterRepository;
import com.zdzimi.blog.exception.ChapterExcepion;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    private ChapterRepository chapterRepository;
    private ArticleService articleService;

    @Autowired
    public ChapterService(ChapterRepository chapterRepository,
                          ArticleService articleService) {
        this.chapterRepository = chapterRepository;
        this.articleService = articleService;
    }

    public List<Chapter> findAll(){
        return chapterRepository.findAll();
    }

    public Chapter findByChapterTitle(String title){
        return chapterRepository.findByChapterTitle(title).orElseThrow(() -> new ChapterExcepion(title));
    }

    public List<Article> findArticlesFromChapter(String title) {
        return findByChapterTitle(title).getArticles();
    }

    public void save(Chapter chapter) {
        chapterRepository.save(chapter);
    }

    public void delete(Chapter chapter) {
        articleService.deleteArticlesFromChapter(chapter);
        chapterRepository.delete(chapter);
    }

    public List<String> findAllChaptersTitles() {
        return findAll().stream()
                .map(Chapter::getChapterTitle)
                .collect(Collectors.toList());
    }
}
