package com.example.online_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String showIndexPage(){
        return "admin/index";
    }

    @GetMapping("/users")
    public String showUsersPage(){
        return "admin/users";
    }

    @GetMapping("/add-category")
    public String showAddCategoryPage(){
        return "admin/add-category";
    }

    @GetMapping("/categories")
    public String showCategoriesPage(){
        return "admin/categories";
    }

    @GetMapping("/add-seller")
    public String showAddSellerPage(){
        return "admin/add-seller";
    }

    @GetMapping("/sellers")
    public String showSellersPage(){
        return "admin/sellers";
    }

    @GetMapping("/products")
    public String showProductsPage(){
        return "admin/products";
    }


}
