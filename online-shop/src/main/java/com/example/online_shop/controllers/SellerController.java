package com.example.online_shop.controllers;

import com.example.online_shop.dto.CategoryDto;
import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.services.CategoryService;
import com.example.online_shop.services.CustomUserDetail;
import com.example.online_shop.services.ProductService;
import com.example.online_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model){
        String sellerName = getSellerName();

        model.addAttribute("sellerName", sellerName);
        return "seller/index";
    }

    //create product
    @GetMapping("/add-product")
    public String showAddProductPage(Model model){
        String sellerName = getSellerName();

        ProductDto product = new ProductDto();
        List<CategoryDto> categories = categoryService.getAllCategories();

        model.addAttribute("product",product);
        model.addAttribute("categories", categories);
        model.addAttribute("sellerName", sellerName);

        return "seller/add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") ProductDto productDto, Model model){
        String sellerName = getSellerName();
        int sellerId = userService.getUserId(sellerName);

        Product product = new Product();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(productDto.getCategory());
        product.setImagePath(productDto.getImagePath());
        product.setSellerId(sellerId);
//        todo upload image

        model.addAttribute("sellerName", sellerName);
        return "/seller/my-products";
    }

    //edit product
    @GetMapping("/edit-product/{id}")
    public String showEditPage(Model model){

        return "seller/edit-product";
    }

    //my products
    @GetMapping("/my-products")
    public String showMyProduct(Model model){
        String sellerName = getSellerName();
        List<Product> products = productService.findProductsBySellerId(userService.getUserId(sellerName));

        model.addAttribute("products", products);
        model.addAttribute("sellerName", sellerName);

        return "seller/my-products";
    }

    private String getSellerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        return userDetails.getName();
    }
}
