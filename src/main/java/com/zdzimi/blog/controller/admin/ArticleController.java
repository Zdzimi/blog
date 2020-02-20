package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.dao.ArticleRepository;
import com.zdzimi.blog.dao.ChapterRepository;
import com.zdzimi.blog.dao.CommentRepository;
import com.zdzimi.blog.dao.ParagraphRepository;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Chapter;
import com.zdzimi.blog.model.Comment;
import com.zdzimi.blog.model.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class ArticleController {

    private ChapterRepository chapterRepository;
    private ArticleRepository articleRepository;
    private ParagraphRepository paragraphRepository;
    private CommentRepository commentRepository;

    @Autowired
    public ArticleController(ChapterRepository chapterRepository,
                             ArticleRepository articleRepository,
                             ParagraphRepository paragraphRepository,
                             CommentRepository commentRepository) {
        this.chapterRepository = chapterRepository;
        this.articleRepository = articleRepository;
        this.paragraphRepository = paragraphRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping("/articles")
    public String showArticleController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("chapters", chapterRepository.findAll());
        return "articles";
    }

    @RequestMapping("/articles-by-chapter")
    public String showArticleControllerWithArticlesByChapter(@RequestParam String chapterTitle, Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        Chapter chapter = chapterRepository.findByChapterTitle(chapterTitle);
        model.addAttribute("articles", articleRepository.findByChapter(chapter));
        model.addAttribute("chapters", chapterRepository.findAll());
        return "articles";
    }

    @RequestMapping("/delete-article")
    public String deleteArticle(String articleTitle){
        Article articleToDelete = articleRepository.findByArticleTitle(articleTitle);

        List<Comment> commentsToDelete = commentRepository.findByArticle(articleToDelete);
        for (int i = 0; i < commentsToDelete.size(); i++) {
            commentRepository.delete(commentsToDelete.get(i));
        }

        List<Paragraph> paragraphsToDelete = paragraphRepository.findByArticle(articleToDelete);
        for (int i = 0; i < paragraphsToDelete.size(); i++) {
            paragraphRepository.delete(paragraphsToDelete.get(i));
        }

        articleRepository.delete(articleToDelete);

        return "redirect:/articles";
    }

    @RequestMapping("/save-article")
    public String saveArticleEntity(int articleId, String articleTitle, String description, String chapterTitle){
        Chapter chapter = chapterRepository.findByChapterTitle(chapterTitle);
        articleRepository.save(new Article(articleId,articleTitle,description,chapter));
        return "redirect:/articles";
    }
}
