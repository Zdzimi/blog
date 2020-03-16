package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.model.TopbarMenu;
import com.zdzimi.blog.service.TopbarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class TopbarController {

    private TopbarService topbarService;

    @Autowired
    public TopbarController(TopbarService topbarService) {
        this.topbarService = topbarService;
    }

    @RequestMapping("/topbar")
    public String showTopbarController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("top", topbarService.findAll());
        return "topbar";
    }

    @RequestMapping("/delete-topbar")
    public String deleteTopbar(String top){
        TopbarMenu byTop = topbarService.findByTop(top);
        topbarService.delete(byTop);
        return "redirect:/topbar";
    }

    @RequestMapping("/save-top")
    public String saveTopEntity(int tId, String top, String tContent){
        topbarService.save(new TopbarMenu(tId, top, tContent));
        return "redirect:/topbar";
    }
}
