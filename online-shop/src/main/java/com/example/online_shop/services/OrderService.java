package com.example.online_shop.services;

import com.example.online_shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public long getOrdersCount() {
        return orderRepository.count();
    }
}
