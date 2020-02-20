package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.dao.TopbarMenuRepository;
import com.zdzimi.blog.model.TopbarMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class TopbarController {

    private TopbarMenuRepository topbarMenuRepository;

    @Autowired
    public TopbarController(TopbarMenuRepository topbarMenuRepository) {
        this.topbarMenuRepository = topbarMenuRepository;
    }

    @RequestMapping("/topbar")
    public String showTopbarController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("top", topbarMenuRepository.findAll());
        return "topbar";
    }

    @RequestMapping("/delete-topbar")
    public String deleteTopbar(String top){
        TopbarMenu byTop = topbarMenuRepository.findByTop(top);
        topbarMenuRepository.delete(byTop);
        return "redirect:/topbar";
    }

    @RequestMapping("/save-top")
    public String saveTopEntity(int tId, String top, String tContent){
        TopbarMenu save = topbarMenuRepository.save(new TopbarMenu(tId, top, tContent));
        return "redirect:/topbar";
    }
}
