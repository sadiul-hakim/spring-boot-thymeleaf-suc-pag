package com.smartcontact.smartcontactmanager.controllers;

import com.smartcontact.smartcontactmanager.entities.User;
import com.smartcontact.smartcontactmanager.helper.Message;
import com.smartcontact.smartcontactmanager.helper.MsgType;
import com.smartcontact.smartcontactmanager.helper.UserType;
import com.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register_user")
    public String register_user(
            @Valid @ModelAttribute User user,
            @RequestParam(
                    value = "agreement",
                    defaultValue = "false"
            ) boolean agreement,
            BindingResult bindingResult,
            Model model,
            HttpSession session
    ){
        try{

            if(!agreement){
                throw new Exception("You need to agree terms");
            }

            if(bindingResult.hasErrors()){
                model.addAttribute("user",user);
                model.addAttribute("error",bindingResult.toString());
                return "signup";
            }

            user.setRole(UserType.ROLE_USER);
            user.setEnabled(true);
            user.setImageUrl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println(user);

            User saved_user = this.userRepository.save(user);
            model.addAttribute("user",new User());

            session.setAttribute("message",new Message("Registered successfully", MsgType.INFO));
            return "signup";
        }catch (Exception ex){
            ex.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("Server Error : Something went wrong", MsgType.ERROR));
            return "signup";
        }

    }
}
