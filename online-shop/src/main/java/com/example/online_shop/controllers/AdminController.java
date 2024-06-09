package com.example.online_shop.controllers;

import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

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

//    @GetMapping("/products")
//    public String showProductsPage(){
//        return "admin/products";}

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/products"; // This is the name of the Thymeleaf template
    }
}
