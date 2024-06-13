package com.example.online_shop.controllers;

import com.example.online_shop.models.Product;
import com.example.online_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model){
        List<Product> latestProducts = productService.findLatestProducts();
        model.addAttribute("products", latestProducts);
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/products/{name}")
    public String showProduct(@PathVariable("name") String name, Model model){
        Product product = productService.findByName(name);

        model.addAttribute("product", product);
        return "product-detail";
    }
}
