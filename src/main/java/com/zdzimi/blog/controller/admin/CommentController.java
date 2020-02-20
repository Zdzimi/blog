package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.dao.ArticleRepository;
import com.zdzimi.blog.dao.CommentRepository;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class CommentController {

    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentController(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping("/comments")
    public String showCommentController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("comments",commentRepository.findAll());
        model.addAttribute("articles",articleRepository.findAll());
        return "comments";
    }

    @RequestMapping("/comments-by-article")
    public String showCommentControllerWithCommentsByArticle(@RequestParam String articleTitle, Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        Article article = articleRepository.findByArticleTitle(articleTitle);
        model.addAttribute("comments",commentRepository.findByArticle(article));
        model.addAttribute("articles", articleRepository.findAll());
        return "comments";
    }

    @RequestMapping("/delete-comment")
    public String deleteCommentEntity(int commentId){
        commentRepository.deleteById(commentId);
        return "redirect:/comments";
    }

    @RequestMapping("/answer")
    public String writeAnswer(int commentId, String admin, String answer){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            comment.get().setAdmin(admin);
            comment.get().setAnswer(answer);
            commentRepository.save(comment.get());
        }
        return "redirect:/comments";
    }
}
