package com.example.online_shop.controllers;

import com.example.online_shop.models.Cart;
import com.example.online_shop.models.User;
import com.example.online_shop.services.CartService;
import com.example.online_shop.services.CustomUserDetail;
import com.example.online_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard/cart")
    public String cart(Model model){
        int userId = getUserDetails().getId();
        List<Cart> items = cartService.getCart(userId);
        Double totalPrice = cartService.getTotalPriceByUser(userId);
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("items", items);
        return "user/cart";
    }

    @GetMapping("/dashboard/cart/add/{name}")
    public String add(@PathVariable String name, Model model, RedirectAttributes redirectAttributes){
        int userId = getUserDetails().getId();

        if (cartService.addToCart(name, userId)) {
            redirectAttributes.addFlashAttribute("success", "item added to cart successfully");
        } else
            redirectAttributes.addFlashAttribute("fail", "item already exist you can select more!");

        return "redirect:/dashboard/cart";
    }

    @DeleteMapping("/dashboard/cart/delete/{name}")
    public String delete(@PathVariable String name, RedirectAttributes redirectAttributes){
        int userId = getUserDetails().getId();
        cartService.deleteByNameAndUserId(name, userId);
        redirectAttributes.addFlashAttribute("deleted", "item removed successfully !");
        return "redirect:/dashboard/cart";
    }

    @PostMapping("/dashboard/cart/updateQuantity")
    public ResponseEntity<?> updateQuantity(@RequestBody UpdateQuantityRequest updateRequest) {
        boolean success = cartService.updateQuantity(updateRequest.getProductId(), updateRequest.getUserId(), updateRequest.getQuantity());
        if (success) {
            return ResponseEntity.ok("Quantity updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update quantity");
        }
    }

    @GetMapping("/dashboard/checkout")
    public String checkout(Model model){
        int userId = getUserDetails().getId();
        List<Cart> items = cartService.getCart(userId);
        model.addAttribute("items", items);

        return "user/order-confirmation";
    }

    public static class UpdateQuantityRequest {
        private int productId;
        private int userId;
        private int quantity;

        // Getters and setters
        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }











    private CustomUserDetail getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetail) authentication.getPrincipal();
    }






}
