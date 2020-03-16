package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.model.Chapter;
import com.zdzimi.blog.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class ChapterController {

    private ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @RequestMapping("/chapters")
    public String showChapterController(Model model){
        model.addAttribute("nav",ADMIN_NAVIGATION);
        model.addAttribute("chapters", chapterService.findAll());
        return "chapters";
    }

    @RequestMapping("/delete-chapter")
    public String deleteChapterByTitle(String chapterTit){
        Chapter chapterToDelete = chapterService.findByChapterTitle(chapterTit);
        chapterService.delete(chapterToDelete);
        return "redirect:/chapters";
    }

    @RequestMapping("/save-chapter")
    public String saveChapter(int chapterId, String chapterTitle){
        chapterService.save(new Chapter(chapterId, chapterTitle));
        return "redirect:/chapters";
    }
}
