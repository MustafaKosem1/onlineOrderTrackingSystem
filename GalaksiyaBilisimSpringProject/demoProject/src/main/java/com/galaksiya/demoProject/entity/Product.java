package com.galaksiya.demoProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(max = 50, message = "Name length must be smaller than 50")
    @Column(name = "name")
    private String name;

    @Size(max = 50, message = "Category length must be smaller than 50")
    @Column(name = "category")
    private String category;


    @Column(name = "description")
    private String description;

    //beklenen teslim süresi uzunluğu gün cinsinden
    @Min(value = 0)
    @Column(name = "delivery_time")
    private int delivery_time;

    @Min(value = 0,message = "Stock '0' değerinden büyük olmalı")
    @Column(name = "stock")
    private int stock;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(name = "price")
    private double price;

    public Product(){

    }

    public Product(String name, String category, String description, int delivery_time, int stock, double price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.delivery_time = delivery_time;
        this.stock = stock;
        this.price = price;
    }

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
}
