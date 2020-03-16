package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Chapter;
import com.zdzimi.blog.service.ArticleService;
import com.zdzimi.blog.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class ArticleController {

    private ChapterService chapterService;
    private ArticleService articleService;

    @Autowired
    public ArticleController(ChapterService chapterService,
                             ArticleService articleService) {
        this.chapterService = chapterService;
        this.articleService = articleService;
    }

    @RequestMapping("/articles")
    public String showArticleController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("articles", articleService.findAll());
        model.addAttribute("chapters", chapterService.findAll());
        return "articles";
    }

    @RequestMapping("/articles-by-chapter")
    public String showArticleControllerWithArticlesByChapter(@RequestParam String chapterTitle, Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        List<Chapter> chapters = chapterService.findAll();
        model.addAttribute("chapters", chapters);
        model.addAttribute("articles", articleService.findArticlesByChapterTitle(chapters, chapterTitle));
        return "articles";
    }

    @RequestMapping("/delete-article")
    public String deleteArticle(String articleTitle){
        Article articleToDelete = articleService.findByArticleTitle(articleTitle);
        articleService.deleteAllFromArticle(articleToDelete);
        return "redirect:/articles";
    }

    @RequestMapping("/save-article")
    public String saveArticleEntity(int articleId, String articleTitle, String description, String chapterTitle){
        Chapter chapter = chapterService.findByChapterTitle(chapterTitle);
        articleService.save(new Article(articleId,articleTitle,description,chapter));
        return "redirect:/articles";
    }
}
