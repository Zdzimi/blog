package com.zdzimi.blog.controller.user;

import com.zdzimi.blog.dao.*;
import com.zdzimi.blog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showHomePage(){
        ModelAndView modelAndView = new ModelAndView("home.jsp");
        List<String> topMenuList = UsersNavigation.getTopMenuList(topbarMenuRepository);
        modelAndView.addObject("topMenu",topMenuList);

        List<String> chapterList = UsersNavigation.getChaptersList(chapterRepository);
        modelAndView.addObject("chapters",chapterList);

        return modelAndView;
    }

    @RequestMapping("/hello")
    public ModelAndView showTopbarEntity (@RequestParam String top){
        ModelAndView modelAndView = new ModelAndView("home.jsp");
        List<String> topMenuList = UsersNavigation.getTopMenuList(topbarMenuRepository);
        modelAndView.addObject("topMenu",topMenuList);

        List<String> chapterList = UsersNavigation.getChaptersList(chapterRepository);
        modelAndView.addObject("chapters",chapterList);

        TopbarMenu topbarMenuEntity = topbarMenuRepository.findByTop(top);
        modelAndView.addObject("content", topbarMenuEntity);
        return modelAndView;
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/chapter")
    public ModelAndView showChapter(@RequestParam String title){
        ModelAndView modelAndView = new ModelAndView("home.jsp");

        List<String> topMenuList = UsersNavigation.getTopMenuList(topbarMenuRepository);
        modelAndView.addObject("topMenu",topMenuList);

        List<String> chapterList = UsersNavigation.getChaptersList(chapterRepository);
        modelAndView.addObject("chapters",chapterList);

        Chapter chapter = chapterRepository.findByChapterTitle(title);
        List<Article> articleList = articleRepository.findByChapter(chapter);
        modelAndView.addObject("articleList", articleList);

        return modelAndView;
    }

    /*---------------------------------------------------------------------------------------*/

    @RequestMapping("/article")
    public ModelAndView showArticle(@RequestParam String title){
        ModelAndView modelAndView = new ModelAndView("article.jsp");

        List<String> topMenuList = UsersNavigation.getTopMenuList(topbarMenuRepository);
        modelAndView.addObject("topMenu",topMenuList);

        List<String> chapterList = UsersNavigation.getChaptersList(chapterRepository);
        modelAndView.addObject("chapters",chapterList);

        Article article = articleRepository.findByArticleTitle(title);
        List<Paragraph> paragraphList = paragraphRepository.findByArticle(article);
        modelAndView.addObject("paragraphList", paragraphList);

        List<Comment> commentList = commentRepository.findByArticle(article);
        modelAndView.addObject("commentList", commentList);
        return modelAndView;
    }

    @RequestMapping("/addComment")
    public String addComment(String username, String commentContent, String articleTitle){
        Article article = articleRepository.findByArticleTitle(articleTitle);
        Comment comment = new Comment(username,commentContent,article);
        commentRepository.save(comment);
        return "redirect:/article?title="+articleTitle;
    }
}
