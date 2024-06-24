package com.example.online_shop.controllers;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
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
@RequestMapping("/dashboard")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private ProductService productService;


    @GetMapping()
    public String dashboard(Model model) {
        User user = userService.findById(getUserDetails().getId());
        model.addAttribute("user", user);
        return "user/dashboard";
    }

    @PostMapping("/updateAddress")
    public String updateAddress(@RequestParam("address") String address, Model model){
        User user = userService.findById(getUserDetails().getId());
        userService.updateAddress(user.getId(), address);
        model.addAttribute("user", user);
        model.addAttribute("success", "Address updated successfully!");
        return "user/dashboard";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        int userId = getUserDetails().getId();
        List<Wishlist> wishlists = wishlistService.getWishlist(userId);
        model.addAttribute("wishlists", wishlists);
        return "user/wishlist";
    }

    @GetMapping("/wishlist/add/{name}")
    public String addProductToWishList(@PathVariable String name, Model model, RedirectAttributes redirectAttributes) {
        int userId = getUserDetails().getId();

        if (wishlistService.addToWishlist(name, userId)) {
            redirectAttributes.addFlashAttribute("success", "item added to wish list successfully");
        } else
            redirectAttributes.addFlashAttribute("fail", "item already exist !");

        return "redirect:/dashboard/wishlist";
    }

    @GetMapping("/wishlist/delete/{name}")
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