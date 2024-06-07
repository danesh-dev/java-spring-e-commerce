package com.example.online_shop.controllers;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/register")
    public String register(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String create( @ModelAttribute("user") UserDto userDto, Model model, Errors errors) {

         userService.create(userDto);
         model.addAttribute("message", "registered successfully !");
         return "register";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @GetMapping("/user-panel")
//    public String user(Model model, Principal principal){
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", userDetails);
//        return "user-panel";
//    }
//
//    @GetMapping("/admin-panel")
//    public String admin(Model model, Principal principal){
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", userDetails);
//        return "admin-panel";
//    }


}
