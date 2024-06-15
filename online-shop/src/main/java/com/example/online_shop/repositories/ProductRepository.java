package com.example.online_shop.repositories;

import com.example.online_shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findById(int id);

    public Product findByName(String name);

    public boolean existsByNameAndCategory(String name, String category);

    public List<Product> findBySellerId(int sellerId);

    public List<Product> findTop3ByOrderByCreatedAtDesc();

    @Query("SELECT COUNT(p) FROM Product p")
    public long countProducts();
}
