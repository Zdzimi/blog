package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.service.ArticleService;
import com.zdzimi.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class CommentController {

    private ArticleService articleService;
    private CommentService commentService;

    @Autowired
    public CommentController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @RequestMapping("/comments")
    public String showCommentController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("comments",commentService.findAll());
        return "comments";
    }

    @RequestMapping("/comments-by-article")
    public String showCommentControllerWithCommentsByArticle(@RequestParam String articleTitle, Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        Article article = articleService.findByArticleTitle(articleTitle);
        model.addAttribute("comments",commentService.findCommentsFromArticle(article));
        return "comments";
    }

    @RequestMapping("/delete-comment")
    public String deleteCommentEntity(int commentId){
        commentService.deleteById(commentId);
        return "redirect:/comments";
    }

    @RequestMapping("/answer")
    public String writeAnswer(int commentId, String admin, String answer){
        commentService.updateComment(commentId, admin, answer);
        return "redirect:/comments";
    }
}
