package com.example.online_shop.dto;

import com.example.online_shop.models.Order;
import com.example.online_shop.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentDto {
    private User user;
    private double amount;
    private List<Order> orders;
    private LocalDateTime paidAt;

    public PaymentDto(User user, double amount, LocalDateTime paidAt, List<Order> orders){
        this.user = user;
        this.amount = amount;
        this.orders = orders;
        this.paidAt = paidAt;
    }

    public PaymentDto(){

    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
}
