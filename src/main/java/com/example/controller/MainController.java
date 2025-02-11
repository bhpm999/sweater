package com.example.controller;

import com.example.domain.Message;
import com.example.domain.User;
import com.example.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String main(Model model){
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Model model){
        Message message = new Message(text, tag, user);
        messageRepo. save(message);

        Iterable<Message> messages = messageRepo.findAll();
        System.out.println(messages);
        model.addAttribute("messages",messages);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        }else
            messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

}
