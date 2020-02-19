package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.dao.ArticleRepository;
import com.zdzimi.blog.dao.CommentRepository;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showCommentController(){
        ModelAndView modelAndView = new ModelAndView("commentController.jsp");
        modelAndView.addObject("nav", ADMIN_NAVIGATION);
        modelAndView.addObject("comments",commentRepository.findAll());
        modelAndView.addObject("articles",articleRepository.findAll());
        return modelAndView;
    }

    @RequestMapping("/comments-by-article")
    public ModelAndView showCommentControllerWithCommentsByArticle(@RequestParam String articleTitle){
        ModelAndView modelAndView = new ModelAndView("commentController.jsp");
        modelAndView.addObject("nav", ADMIN_NAVIGATION);
        Article article = articleRepository.findByArticleTitle(articleTitle);
        modelAndView.addObject("comments",commentRepository.findByArticle(article));
        modelAndView.addObject("articles", articleRepository.findAll());
        return modelAndView;
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
