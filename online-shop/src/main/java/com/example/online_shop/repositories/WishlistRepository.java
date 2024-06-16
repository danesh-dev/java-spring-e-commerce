package com.example.online_shop.repositories;

import com.example.online_shop.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    public List<Wishlist> findByUserId(String userId);
}
