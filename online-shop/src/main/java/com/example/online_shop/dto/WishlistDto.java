package com.example.online_shop.dto;
import jakarta.validation.constraints.NotNull;

public class WishlistDto {
    @NotNull
    private String name;

    @NotNull
    private String imagePath;

    @NotNull
    private int price;

    @NotNull
    private int userId;

    public WishlistDto(String name, String imagePath, int price, int userId) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.userId = userId;
    }

    @NotNull
    public int getPrice() {
        return price;
    }

    public void setPrice(@NotNull int price) {
        this.price = price;
    }

    public @NotNull String getImagePath() {
        return imagePath;
    }

    public void setImagePath(@NotNull String imagePath) {
        this.imagePath = imagePath;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public int getUserId() {
        return userId;
    }

    public void setUserId(@NotNull int userId) {
        this.userId = userId;
    }
}
