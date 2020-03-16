package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Paragraph;
import com.zdzimi.blog.model.Photo;
import com.zdzimi.blog.service.ArticleService;
import com.zdzimi.blog.service.ParagraphService;
import com.zdzimi.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class ParagraphController {

    private ArticleService articleService;
    private ParagraphService paragraphService;
    private PhotoService photoService;
    @Autowired
    public ParagraphController(ArticleService articleService,
                               ParagraphService paragraphService,
                               PhotoService photoService) {
        this.articleService = articleService;
        this.paragraphService = paragraphService;
        this.photoService = photoService;
    }

    @RequestMapping("/paragraphs")
    public String showParagraphController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("paragraphs", paragraphService.findAll());
        model.addAttribute("articles", articleService.findAll());
        model.addAttribute("photos",photoService.findAll());
        return "paragraph";
    }

    @RequestMapping("/paragraphs-by-article")
    public String showParagraphControllerWithParagraphsByArticle(@RequestParam String articleTitle, Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        List<Article> articles = articleService.findAll();
        model.addAttribute("photos",photoService.findAll());
        model.addAttribute("articles", articles);
        Article article = articleService.findByArticleTitle(articles, articleTitle);
        model.addAttribute("paragraphs", paragraphService.findParagraphsFromArticle(article));
        return "paragraph";
    }

    @RequestMapping("/delete-paragraph")
    public String deleteParagraph(int pId){
        paragraphService.deleteById(pId);
        return "redirect:/paragraphs";
    }

    @RequestMapping("/save-paragraph")
    public String saveParagraph(int pId, String header, String content, String articleTitle, long photoId){
        Article article = articleService.findByArticleTitle(articleTitle);
        Photo photo = photoService.findByIdOrNull(photoId);
        paragraphService.save(new Paragraph(pId,header,content,photo,article));
        return "redirect:/paragraphs";
    }
}
