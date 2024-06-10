package com.example.online_shop.models;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //private String imagePath = ........... -> to set the default image path if not given or uploaded bu seller
    private String imagePath;

    private int price;

    private int stock;

    private String category;

    private String seller;

    public Product(String name, String imagePath, int price, int stock, String category, String seller) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.seller = seller;
    }

    public Product(){}

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
