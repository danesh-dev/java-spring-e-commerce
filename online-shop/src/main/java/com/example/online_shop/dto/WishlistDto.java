package com.example.online_shop.dto;
import com.example.online_shop.models.Product;
import jakarta.validation.constraints.NotNull;

public class WishlistDto {
    @NotNull
    private Product product;

    @NotNull
    private int userId;

    public WishlistDto(Product product, int userId) {
        this.product = product;
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
