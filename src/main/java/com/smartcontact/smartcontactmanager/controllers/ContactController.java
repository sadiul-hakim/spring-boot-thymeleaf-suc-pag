package com.smartcontact.smartcontactmanager.controllers;

import com.smartcontact.smartcontactmanager.entities.Contact;
import com.smartcontact.smartcontactmanager.entities.User;
import com.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ContactController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("add_contact")
    public String add_contact(@ModelAttribute Contact contact, Principal principal){
        String username= principal.getName();
        User user=userRepository.findUserByEmail(username);



        return "normal/add_contact";
    }
}
