package com.test.demo.Controllers;

import com.test.demo.domain.Message;
import com.test.demo.domain.User;
import com.test.demo.repository.MessageRepository;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("title", "Главная");

        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        //??
        Iterable<User> user = userRepository.findAll();
        model.addAttribute("usr", user);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("logUser", auth.getName());

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {

        model.addAttribute("title", "Про нас");

        return "about";
    }


}