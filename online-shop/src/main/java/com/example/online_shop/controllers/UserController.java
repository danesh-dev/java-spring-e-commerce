package com.example.online_shop.controllers;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.Wishlist;
import com.example.online_shop.services.CustomUserDetail;
import com.example.online_shop.services.ProductService;
import com.example.online_shop.services.UserService;
import com.example.online_shop.services.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private ProductService productService;

    //register
    @GetMapping("/register")
    public String register(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "register";
        }

        try {
            userService.create(userDto);
            model.addAttribute("message", "Registered successfully!");
            return "redirect:/login?email=" + userDto.getEmail();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    //login
    @GetMapping("/login")
    public String login(@RequestParam(value = "email", required = false) String email, Model model) {
        model.addAttribute("email", email);
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.verifyPassword(password, email)) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }


    //user dashboard
    @GetMapping("/dashboard")
    public String panel() {
        return "user-panel";
    }

    @GetMapping("/dashboard/wishlist")
    public String wishlist(Model model) {
        int userId = getUserDetails().getId();
        List<Wishlist> wishlists = wishlistService.getWishlist(userId);
        model.addAttribute("wishlists", wishlists);
        return "user/wishlist";
    }

    @GetMapping("/dashboard/wishlist/add/{name}")
    public String addProductToWishList(@PathVariable String name, Model model, RedirectAttributes redirectAttributes) {
        int userId = getUserDetails().getId();

        if (wishlistService.addToWishlist(name, userId)) {
            redirectAttributes.addFlashAttribute("success", "item added to wish list successfully");
        } else
            redirectAttributes.addFlashAttribute("fail", "item already exist !");

        return "redirect:/dashboard/wishlist";
    }

    @GetMapping("/dashboard/wishlist/delete/{name}")
    public String delete(@PathVariable String name, RedirectAttributes redirectAttributes){
        int userId = getUserDetails().getId();
        wishlistService.deleteByNameAndUserId(name, userId);
        redirectAttributes.addFlashAttribute("deleted", "item removed successfully !");
        return "redirect:/dashboard/wishlist";
    }


    private CustomUserDetail getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetail) authentication.getPrincipal();
    }
}