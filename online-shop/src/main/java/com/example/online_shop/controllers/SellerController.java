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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


    //my products
    @GetMapping("/my-products")
    public String showMyProducts(Model model){
        String sellerName = getSellerName();
        List<Product> products = productService.findProductsBySellerId(userService.getUserId(sellerName));

        System.out.println(products.size());
        model.addAttribute("products", products);
        model.addAttribute("sellerName", sellerName);

        return "seller/my-products";
    }


    //update
    @GetMapping("/products/update/{name}")
    public String showUpdatePage(@PathVariable("name") String name, Model model){
        ProductDto product = new ProductDto();
        Product chosen_product = productService.findByName(name);
        product.setName(chosen_product.getName());
        product.setPrice(chosen_product.getPrice());
        product.setStock(chosen_product.getStock());
        product.setSellerId(chosen_product.getSellerId());
        product.setCategory(chosen_product.getCategory());

        model.addAttribute("product", product);
        return "/seller/edit-product";
    }

    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute("product") ProductDto productDto, Model model){
        try {
            productService.updateProduct(productDto);
            model.addAttribute("message", "محصول با موفقیت ویرایش شد");
        }catch (IOException e){
            model.addAttribute("message", e.getMessage());
        }

        return "redirect:/seller/my-products";
    }


    //delete
    @DeleteMapping("products/delete/{name}")
    public String deleteProduct(@PathVariable String name){
        productService.deleteProduct(productService.findByName(name));

        return "redirect:/seller/my-product";
    }



    //---------------------------------------------------------
    private String getSellerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        return userDetails.getName();
    }
}
