package com.test.demo.Controllers;

import com.test.demo.domain.Message;
import com.test.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class RedactController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/detail")
    public String redact(Model model, Model mainname, Model logUser) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        mainname.addAttribute("title", "Redact");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logUser.addAttribute("logUser", auth.getName());
        System.out.println(auth.getName());

        return "detail";
    }

    @GetMapping("/red/{id}")
    public String redactApply(@PathVariable(value = "id") Integer id, Model model) {

        if(!messageRepository.existsById(id)) {
            return "redirect:/";
        }

        Optional<Message> messages = messageRepository.findById(id);
        ArrayList<Message> res = new ArrayList<>();
        messages.ifPresent(res::add);

        model.addAttribute("messages", res);

        return "update";
    }

    @PostMapping("/red/{id}")
    public String redactUpdate(@PathVariable(value = "id") Integer id, @RequestParam String text,
                               @RequestParam String typText) {

        Message message = messageRepository.findById(id).orElseThrow();
        message.setTypText(typText);
        message.setText(text);
        message.setDates();
        messageRepository.save(message);

        return "redirect:/detail";
    }

    @PostMapping("/red/{id}/remove")
    public String redactRemove(@PathVariable(value = "id") Integer id) {

        Message message = messageRepository.findById(id).orElseThrow();
        messageRepository.delete(message);

        return "redirect:/detail";
    }


}
