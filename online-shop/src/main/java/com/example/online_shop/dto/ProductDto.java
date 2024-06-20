package com.example.online_shop.dto;

import com.example.online_shop.models.Category;
import com.example.online_shop.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {

    @Size(min = 3, max = 100, message = "Name must be more than 3 characters!")
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String imagePath = "/assets/upload/default-image.jpeg";

    @Positive(message = "Product can't be free")
    private int price;

    @Positive(message = "Inventory can't be empty")
    private int stock;

    @NotBlank(message = "Category not added!")
    private Category category;

    private User seller;

    private String description = "default description";

    public ProductDto() {
    }

    public ProductDto(String name, String imagePath, int price, int stock, Category category, User seller, String description) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.seller = seller;
        this.description = description;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
