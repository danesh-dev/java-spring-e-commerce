package com.example.online_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String index(){
        return "admin/index";
    }

    @GetMapping("/users")
    public String showUsersPage(){
        return "admin/users";
    }

    @GetMapping("/add-product")
    public String showAddProductPage(){
        return "admin/add-product";
    }

}
