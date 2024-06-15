package com.example.online_shop.services;

import com.example.online_shop.models.Wishlist;
import com.example.online_shop.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> getWishlist(int id){
        return WishlistRepository.findByUserId(id);
    }
}
