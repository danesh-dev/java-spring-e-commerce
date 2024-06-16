package com.example.online_shop.services;

import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import com.example.online_shop.models.Wishlist;
import com.example.online_shop.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<Wishlist> getWishlist(int id){
        return wishlistRepository.findByUserId(id);
    }

    @Transactional
    public boolean addToWishlist(String name, int userId) {

        if (wishlistRepository.existsByNameAndUserId(name, userId)) {
            return false;
        }else {

            Product product = productService.findByName(name);
            User user = userService.findById(userId);

            Wishlist wishlist = new Wishlist();
            wishlist.setName(name);
            wishlist.setPrice(product.getPrice());
            wishlist.setUserId(user.getId());
            wishlist.setImagePath(product.getImagePath());
            wishlistRepository.save(wishlist);
            return true;
        }
    }
}
