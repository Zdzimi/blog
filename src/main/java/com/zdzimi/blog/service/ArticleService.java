package com.zdzimi.blog.service;

import com.zdzimi.blog.dao.ArticleRepository;
import com.zdzimi.blog.exception.ArticleException;
import com.zdzimi.blog.exception.ChapterExcepion;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private ParagraphService paragraphService;
    private CommentService commentService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          ParagraphService paragraphService,
                          CommentService commentService) {
        this.articleRepository = articleRepository;
        this.paragraphService = paragraphService;
        this.commentService = commentService;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findByArticleTitle(String title) {
        return articleRepository.findByArticleTitle(title)
                .orElseThrow(() -> new ArticleException(title));
    }

    public Article findByArticleTitle(List<Article> articles, String title) {
        return articles.stream()
                .filter(article -> article.getArticleTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new ArticleException(title));
    }

    public List<Article> findArticlesByChapterTitle(List<Chapter> chapters, String chapterTitle) {
        return chapters.stream()
                .filter(chapter -> chapter.getChapterTitle().equals(chapterTitle))
                .findFirst()
                .orElseThrow(() -> new ChapterExcepion(chapterTitle))
                .getArticles();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public void deleteArticlesFromChapter(Chapter chapter) {
        chapter.getArticles().forEach(this::deleteAllFromArticle);
    }

    public void deleteAllFromArticle(Article article) {
        paragraphService.deleteParagraphsByAtricle(article);
        commentService.deleteCommentsByArticle(article);
        articleRepository.delete(article);
    }
}
