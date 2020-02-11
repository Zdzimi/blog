package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.dao.TopbarMenuRepository;
import com.zdzimi.blog.model.TopbarMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class TopbarController {

    private TopbarMenuRepository topbarMenuRepository;

    @Autowired
    public TopbarController(TopbarMenuRepository topbarMenuRepository) {
        this.topbarMenuRepository = topbarMenuRepository;
    }

    @RequestMapping("/topbar")
    public ModelAndView showTopbarController(){
        ModelAndView modelAndView = new ModelAndView("topbar.jsp");
        modelAndView.addObject("nav", ADMIN_NAVIGATION);
        List<TopbarMenu> all = topbarMenuRepository.findAll();
        modelAndView.addObject("top", all);
        return modelAndView;
    }

    @RequestMapping("/deleteTopbar")
    public String deleteTopbar(String top){
        TopbarMenu byTop = topbarMenuRepository.findByTop(top);
        topbarMenuRepository.delete(byTop);
        return "redirect:/topbar";
    }

    @RequestMapping("/saveTopEntity")
    public String saveTopEntity(int tId, String top, String tContent){
        TopbarMenu save = topbarMenuRepository.save(new TopbarMenu(tId, top, tContent));
        return "redirect:/topbar";
    }
}
