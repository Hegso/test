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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ApplyController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/apply")
    public String apply(Map<String, Object> model, Model mainname, Model logUser) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);

        Iterable<User> usr = userRepository.findAll();
        model.put("usr", usr);

        mainname.addAttribute("title", "Apply - Новое обращение");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logUser.addAttribute("logUser", auth.getName());

        return "/apply";
    }

    @PostMapping("/apply")
    public String add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String lgn,
                      @RequestParam String email, @RequestParam String text, @RequestParam String typText,
                      Map<String, Object> model) {

        Message message = new Message(firstName, lastName, lgn, email, text, typText);

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);

        return "redirect:/detail";
    }
}
