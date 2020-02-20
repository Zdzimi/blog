package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.dao.ArticleRepository;
import com.zdzimi.blog.dao.ParagraphRepository;
import com.zdzimi.blog.dao.PhotoRepository;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Paragraph;
import com.zdzimi.blog.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class ParagraphController {

    private ArticleRepository articleRepository;
    private ParagraphRepository paragraphRepository;
    private PhotoRepository photoRepository;

    @Autowired
    public ParagraphController(ArticleRepository articleRepository,
                               ParagraphRepository paragraphRepository,
                               PhotoRepository photoRepository) {
        this.articleRepository = articleRepository;
        this.paragraphRepository = paragraphRepository;
        this.photoRepository = photoRepository;
    }

    @RequestMapping("/paragraphs")
    public String showParagraphController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("paragraphs", paragraphRepository.findAll());
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("photos",photoRepository.findAll());
        return "paragraph";
    }

    @RequestMapping("/paragraphs-by-article")
    public String showParagraphControllerWithParagraphsByArticle(@RequestParam String articleTitle, Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        Article article = articleRepository.findByArticleTitle(articleTitle);
        model.addAttribute("paragraphs", paragraphRepository.findByArticle(article));
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("photos",photoRepository.findAll());
        return "paragraph";
    }

    @RequestMapping("/delete-paragraph")
    public String deleteParagraph(int pId){
        Optional<Paragraph> paragraph = paragraphRepository.findById(pId);
        paragraph.ifPresent(p -> paragraphRepository.delete(p));
        return "redirect:/paragraphs";
    }

    @RequestMapping("/save-paragraph")
    public String saveParagraph(int pId, String header, String content, String articleTitle, long photoId){
        Article article = articleRepository.findByArticleTitle(articleTitle);
        Photo photo = photoRepository.findById(photoId).orElse(null);
        paragraphRepository.save(new Paragraph(pId,header,content,photo,article));
        return "redirect:/paragraphs";
    }
}
