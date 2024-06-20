package com.example.online_shop.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private BigDecimal amount;

    @Column(name = "paid_at", nullable = false, updatable = false)
    private LocalDateTime paidAt;

    @OneToMany(mappedBy = "payment")
    private List<Order> orders;

    @PrePersist
    protected void onCreate() {
        paidAt = LocalDateTime.now();
    }

    public Payment(int id, User user, BigDecimal amount, LocalDateTime paidAt, List<Order> orders) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.paidAt = paidAt;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}