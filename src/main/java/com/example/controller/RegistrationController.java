package com.example.controller;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepo userRepo;
    @GetMapping("/registration")
    public String registrate(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model) throws InterruptedException {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if(userFromDB != null){
            model.addAttribute("message", "user exists");
            return "registration";
        }
        model.addAttribute("message", "successfully!");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
        return "registration";
    }

}
