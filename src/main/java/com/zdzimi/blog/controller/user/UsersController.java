package com.zdzimi.blog.controller.user;

import com.zdzimi.blog.model.*;
import com.zdzimi.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    private TopbarService topbarService;
    private ChapterService chapterService;
    private ArticleService articleService;
    private ParagraphService paragraphService;
    private CommentService commentService;

    @Autowired
    public UsersController(TopbarService topbarService,
                           ChapterService chapterService,
                           ArticleService articleService,
                           ParagraphService paragraphService,
                           CommentService commentService) {
        this.topbarService = topbarService;
        this.chapterService = chapterService;
        this.articleService = articleService;
        this.paragraphService = paragraphService;
        this.commentService = commentService;
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("topMenu",topbarService.findAllTop());
        model.addAttribute("chapters",chapterService.findAllChaptersTitles());
        return "home";
    }

    @RequestMapping("/hello")
    public String showTopbarEntity (@RequestParam String top, Model model){
        model.addAttribute("topMenu", topbarService.findAllTop());
        model.addAttribute("chapters",chapterService.findAllChaptersTitles());
        model.addAttribute("content", topbarService.findByTop(top));
        return "home";
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/chapter")
    public String showChapter(@RequestParam String title, Model model){
        model.addAttribute("topMenu",topbarService.findAllTop());
        model.addAttribute("chapters", chapterService.findAllChaptersTitles());
        model.addAttribute("articleList",chapterService.findArticlesFromChapter(title));
        return "home";
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/article")
    public String showArticle(@RequestParam String title, Model model){
        model.addAttribute("topMenu",topbarService.findAllTop());
        model.addAttribute("chapters", chapterService.findAllChaptersTitles());
        Article article = articleService.findByArticleTitle(title);
        model.addAttribute("paragraphList", paragraphService.findParagraphsFromArticle(article));
        model.addAttribute("commentList", commentService.findCommentsFromArticle(article));
        return "article";
    }

    @RequestMapping("/add-comment")
    public String addComment(String username, String commentContent, String articleTitle){
        Article article = articleService.findByArticleTitle(articleTitle);
        commentService.save(new Comment(username,commentContent,article));
        return "redirect:/article?title=" + articleTitle;
    }
}
