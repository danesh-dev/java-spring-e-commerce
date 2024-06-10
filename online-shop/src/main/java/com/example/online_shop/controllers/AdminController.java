package com.example.online_shop.controllers;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

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
    public String showAddSellerPage(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "admin/add-seller";
    }

    @PostMapping("/add-seller")
    public String addSeller(@ModelAttribute("user") UserDto userDto, Model model) throws Exception {
        userDto.setRole("SELLER");
        userService.create(userDto);
        model.addAttribute("message", "فروشنده با موفقیت اضافه شد");
        return "admin/sellers";
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
