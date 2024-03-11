package com.galaksiya.demoProject.dto;

import com.galaksiya.demoProject.entity.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class ProductSaveDto {
    @Size(max = 50, message = "Name length must be smaller than 50")
    private String name;

    @Size(max = 50, message = "Category length must be smaller than 50")
    private String category;

    private String description;

    @Min(value = 0)
    private int delivery_time;

    @Min(value = 0,message = "Stock '0' değerinden büyük olmalı")
    private int stock;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private double price;

    public ProductSaveDto(){}

    public ProductSaveDto(String name, String category, String description, int delivery_time, int stock, double price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.delivery_time = delivery_time;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(int delivery_time) {
        this.delivery_time = delivery_time;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product createProductFromProductSaveDto(){
        Product product= new Product();
        product.setName(this.getName());
        product.setCategory(this.getCategory());
        product.setDescription(this.getDescription());
        product.setStock(this.getStock());
        product.setPrice(this.getPrice());
        product.setDelivery_time(this.getDelivery_time());
        return product;
    }
}
