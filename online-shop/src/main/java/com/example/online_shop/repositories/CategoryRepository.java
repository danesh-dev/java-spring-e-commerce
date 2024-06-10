package com.example.online_shop.repositories;

import com.example.online_shop.models.Category;
import com.example.online_shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Product findByName(String name);
}
