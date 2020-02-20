package com.zdzimi.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        return "admin";
    }
}
