package com.smartcontact.smartcontactmanager.controllers;

import com.smartcontact.smartcontactmanager.entities.Contact;
import com.smartcontact.smartcontactmanager.entities.User;
import com.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model model,Principal principal){
        String username= principal.getName();

        User user = userRepository.findUserByEmail(username);

        model.addAttribute("user",user);
    }

    @RequestMapping("/index")
    public String dashboard(Model model){

         model.addAttribute("title","User Dashboard");

        return "normal/user_dashboard";
    }

    @RequestMapping("/add_contact")
    public String add_contact(Model model ){
        model.addAttribute("title","Add Contact");
        model.addAttribute("contact",new Contact());

        return "normal/add_contact";
    }

    @GetMapping("/all-users/{page}")
    public String getAllUsers(@PathVariable("page") Integer page){

        //current page no:
        //user per page
        Pageable of = PageRequest.of(page, 5);
        Page<User> allUsers = userRepository.getAllUsers(of);

        int totalPages= allUsers.getTotalPages();


        return "";
    }
}
