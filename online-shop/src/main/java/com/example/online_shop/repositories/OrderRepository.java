package com.example.online_shop.repositories;

import com.example.online_shop.models.Order;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    List<Order> findByUser(User user);

    Order findByProductAndUser(Product product, User user);
}
