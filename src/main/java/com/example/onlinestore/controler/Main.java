package com.example.onlinestore.controler;

import com.example.onlinestore.model.User;
import com.example.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public String startPage(@AuthenticationPrincipal User user, Model model) throws InterruptedException {
     String user1 = user.getUsername();
        model.addAttribute("username",user1);
        //Thread.sleep(); это задержка
        return "main";

    }
}
