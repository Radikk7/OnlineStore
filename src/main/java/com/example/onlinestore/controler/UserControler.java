package com.example.onlinestore.controler;
import com.example.onlinestore.model.Role;
import com.example.onlinestore.model.User;
import com.example.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserControler {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/userregistration")
    public String user(){
        return "userregistration";
    }
  @PostMapping("/userregistration")
  public String registration(@RequestParam(name ="username")String username,
                             @RequestParam(name = "password")String password, Model model){

      User user = new User();
      user.setUsername(username);
   //   String password1= passwordEncoder.encode(password);
      user.setPassword(password);
    //  user.setEmail(email);
      List<Role>roleList = new ArrayList<>();
      roleList.add(Role.Admin);
      user.setRole(roleList);
      user.setActive(true);
         userRepository.save(user);
        return "redirect:/allproduct";
 }




}
