package com.zdzimi.blog.controller;

import com.zdzimi.blog.dao.UserRepository;
import com.zdzimi.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class Log {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Log(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login.jsp";
    }

    @RequestMapping(value = "/logout-success")
    public String logoutPage(){
        return "logout.jsp";
    }

    @RequestMapping(value = "/registry")
    public String registryPage(Model model){
        model.addAttribute("user",new User());
        return "registry.jsp";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public String processRegistration(@Valid User user, Errors errors){
        if (errors.hasErrors()) {
            return "registry.jsp";
        }
        User user1 = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), "ROLE_USER");
        userRepository.save(user1);
        return "redirect:/";
    }
}
