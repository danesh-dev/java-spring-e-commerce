package com.example.online_shop.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDto {

    @NotBlank(message = "category name not added !")
    private String name;

    public CategoryDto(){

    }
    public CategoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
