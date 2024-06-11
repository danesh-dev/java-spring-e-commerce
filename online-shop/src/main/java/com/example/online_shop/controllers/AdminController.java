package com.example.online_shop.controllers;

import com.example.online_shop.dto.CategoryDto;
import com.example.online_shop.dto.UserDto;
import com.example.online_shop.models.User;
import com.example.online_shop.services.CategoryService;
import com.example.online_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showIndexPage(){
        return "admin/index";
    }

    //user management
    @GetMapping("/users")
    public String showUsersPage(Model model){
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/delete-user/{name}")
    public String deleteUser(@PathVariable String name, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userService.findByName(name));
        redirectAttributes.addFlashAttribute("message", "کاربر با موفقیت حذف شد");
        return "redirect:/admin/users";
    }

    //category management
    @GetMapping("/add-category")
    public String showAddCategoryPage(Model model){
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
        return "admin/add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("category") CategoryDto categoryDto, Model model){
        categoryService.create(categoryDto);
        model.addAttribute("message", "دسته بندی با موفقیت اضافه شد");
        return "admin/categories";
    }

    @GetMapping("/delete-category/{name}")
    public String deleteCategory(@PathVariable String name, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(categoryService.findByName(name));
        redirectAttributes.addFlashAttribute("message", "دسته بندی با موفقیت حذف شد!");
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories")
    public String showCategoriesPage(Model model){
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/categories";
    }

    //seller management
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

    @GetMapping("/delete-seller/{name}")
    public String deleteSeller(@PathVariable String name, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userService.findByName(name));
        redirectAttributes.addFlashAttribute("message", "فروشنده با موفقیت حذف شد!");
        return "redirect:/admin/sellers";
    }

    @GetMapping("/sellers")
    public String showSellersPage(Model model){
        List<User> sellers = userService.getSellers();
        model.addAttribute("sellers", sellers);
        return "admin/sellers";
    }


    //product management
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("/delete-product/{name}")
    public String deleteProduct(@PathVariable String name, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(productService.findByName(name));
        redirectAttributes.addFlashAttribute("message", "محصول با موفقیت حذف شد");
        return "redirect:/admin/products";
    }
}
