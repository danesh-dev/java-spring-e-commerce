package com.example.online_shop.repositories;

import com.example.online_shop.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    public List<Wishlist> findByUserId(int userId);

    @Query("SELECT COUNT(w) > 0 FROM Wishlist w WHERE w.name = :name AND w.userId = :userId")
    boolean existsByNameAndUserId(@Param("name") String name, @Param("userId") int userId);
//    @Query("SELECT FROM wishlists WHERE name = :name AND user.id = :userId")
//    public Wishlist existsByNameAndUserId(@Param("name") String name, @Param("userId") int userId);
}
