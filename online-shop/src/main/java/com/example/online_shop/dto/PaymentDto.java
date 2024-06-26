package com.example.online_shop.dto;

import com.example.online_shop.models.Order;
import com.example.online_shop.models.User;

import java.math.BigDecimal;
import java.util.List;

public class PaymentDto {
    private User user;
    private BigDecimal amount;
    private List<Order> orders;

    public PaymentDto(User user, BigDecimal amount, List<Order> orders){
        this.user = user;
        this.amount = amount;
        this.orders = orders;
    }

    public PaymentDto(){

    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }
}
