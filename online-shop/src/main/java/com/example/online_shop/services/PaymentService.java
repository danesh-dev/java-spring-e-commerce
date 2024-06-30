package com.example.online_shop.services;

import com.example.online_shop.models.Payment;
import com.example.online_shop.models.User;
import com.example.online_shop.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findByUser(User user){
        return paymentRepository.findByUser(user);
    }

    public Payment findById(int id){
        return paymentRepository.findById(id);
    }

    public void create(Payment payment){
        paymentRepository.save(payment);
    }
}
