package com.example.online_shop.services;

import com.example.online_shop.models.Order;
import com.example.online_shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<Order> getOrder(int id){
        return orderRepository.findByUser(userService.findById(id));
    }

    @Transactional
    public void addToOrder(String name, int userId) {
        Order order = new Order();
        order.setProduct(productService.findByName(name));
        order.setUser(userService.findById(userId));
        order.setPayment(null);
        orderRepository.save(order);
    }
}
