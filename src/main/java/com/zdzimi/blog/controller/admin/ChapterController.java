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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class ChapterController {

    private ChapterRepository chapterRepository;
    private ArticleRepository articleRepository;
    private ParagraphRepository paragraphRepository;
    private CommentRepository commentRepository;

    @Autowired
    public ChapterController(ChapterRepository chapterRepository,
                             ArticleRepository articleRepository,
                             ParagraphRepository paragraphRepository,
                             CommentRepository commentRepository) {
        this.chapterRepository = chapterRepository;
        this.articleRepository = articleRepository;
        this.paragraphRepository = paragraphRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping("/chapters")
    public ModelAndView showChapterController(){
        ModelAndView modelAndView = new ModelAndView("chapterController.jsp");
        modelAndView.addObject("nav",ADMIN_NAVIGATION);
        modelAndView.addObject("chapters", chapterRepository.findAll());
        return modelAndView;
    }

    @RequestMapping("/deleteChapter")
    public String deleteChapterByTitle(String chapterTit){
        Chapter chapterToDelete = chapterRepository.findByChapterTitle(chapterTit);

        List<Article> articlesToDelete = articleRepository.findByChapter(chapterToDelete);

        for (int i = 0; i < articlesToDelete.size(); i++) {

            List<Comment> commentsToDelete = commentRepository.findByArticle(articlesToDelete.get(i));
            for (int j = 0; j < commentsToDelete.size(); j++) {
                commentRepository.delete(commentsToDelete.get(j));
            }

            List<Paragraph> paragraphsToDelete = paragraphRepository.findByArticle(articlesToDelete.get(i));
            for (int j = 0; j < paragraphsToDelete.size(); j++) {
                paragraphRepository.delete(paragraphsToDelete.get(j));
            }
            articleRepository.delete(articlesToDelete.get(i));
        }

        chapterRepository.delete(chapterToDelete);

        return "redirect:/chapters";
    }

    @RequestMapping("/saveChapterEntity")
    public String saveChapter(int chapterId, String chapterTitle){
        chapterRepository.save(new Chapter(chapterId, chapterTitle));
        return "redirect:/chapters";
    }
}
