package com.example.online_shop.repositories;

import com.example.online_shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);

    Product findByName(String name);

    boolean existsByNameAndCategory(String name, String category);
}
