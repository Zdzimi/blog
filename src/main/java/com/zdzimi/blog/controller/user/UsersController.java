package com.zdzimi.blog.controller.user;

import com.zdzimi.blog.dao.*;
import com.zdzimi.blog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {

    private TopbarMenuRepository topbarMenuRepository;
    private ChapterRepository chapterRepository;
    private ArticleRepository articleRepository;
    private ParagraphRepository paragraphRepository;
    private CommentRepository commentRepository;

    @Autowired
    public UsersController(TopbarMenuRepository topbarMenuRepository,
                           ChapterRepository chapterRepository,
                           ArticleRepository articleRepository,
                           ParagraphRepository paragraphRepository,
                           CommentRepository commentRepository) {
        this.topbarMenuRepository = topbarMenuRepository;
        this.chapterRepository = chapterRepository;
        this.articleRepository = articleRepository;
        this.paragraphRepository = paragraphRepository;
        this.commentRepository = commentRepository;
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("topMenu",UsersNavigation.getTopMenuList(topbarMenuRepository));
        model.addAttribute("chapters",UsersNavigation.getChaptersList(chapterRepository));
        return "home";
    }

    @RequestMapping("/hello")
    public String showTopbarEntity (@RequestParam String top, Model model){
        model.addAttribute("topMenu",UsersNavigation.getTopMenuList(topbarMenuRepository));
        model.addAttribute("chapters",UsersNavigation.getChaptersList(chapterRepository));
        model.addAttribute("content", topbarMenuRepository.findByTop(top));
        return "home";
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/chapter")
    public String showChapter(@RequestParam String title, Model model){
        model.addAttribute("topMenu",UsersNavigation.getTopMenuList(topbarMenuRepository));
        model.addAttribute("chapters",UsersNavigation.getChaptersList(chapterRepository));
        Chapter chapter = chapterRepository.findByChapterTitle(title);
        List<Article> articleList = articleRepository.findByChapter(chapter);
        model.addAttribute("articleList",articleList);
        return "home";
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/article")
    public String showArticle(@RequestParam String title, Model model){
        model.addAttribute("topMenu",UsersNavigation.getTopMenuList(topbarMenuRepository));
        model.addAttribute("chapters",UsersNavigation.getChaptersList(chapterRepository));

        Article article = articleRepository.findByArticleTitle(title);
        List<Paragraph> paragraphList = paragraphRepository.findByArticle(article);
        model.addAttribute("paragraphList", paragraphList);

        List<Comment> commentList = commentRepository.findByArticle(article);
        model.addAttribute("commentList", commentList);
        return "article";
    }

    @RequestMapping("/add-comment")
    public String addComment(String username, String commentContent, String articleTitle){
        Article article = articleRepository.findByArticleTitle(articleTitle);
        Comment comment = new Comment(username,commentContent,article);
        commentRepository.save(comment);
        return "redirect:/article?title="+articleTitle;
    }
}
