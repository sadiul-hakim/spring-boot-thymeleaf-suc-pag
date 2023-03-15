package com.smartcontact.smartcontactmanager.controllers;

import com.smartcontact.smartcontactmanager.entities.User;
import com.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home Page");

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About Page");

        return "about";
    }

    @GetMapping("/singin")
    public String login(Model model){
        model.addAttribute("title","Login Page");

        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","SignUp Page");
        model.addAttribute("user",new User());

        return "signup";
    }
}
