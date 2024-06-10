package com.example.online_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {

    @Size(min = 3, max = 100, message = "Name must be more than 3 character !")
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String imagePath = "/assets/images/default-image.jpeg";

    @Positive(message = "product can't be free")
    @NotBlank(message = "Price not added !")
    private int price;

    @Positive(message = "inventory can't be empty")
    @NotBlank(message = "Stock not added !")
    private int stock;

    @NotBlank(message = "Category not added !")
    private String category;

    private String seller;

    public ProductDto(String name, String imagePath, int price, int stock, String category, String seller) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath(){
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}