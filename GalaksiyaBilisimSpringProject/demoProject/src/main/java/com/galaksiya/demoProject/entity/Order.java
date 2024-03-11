package com.galaksiya.demoProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //sipariş veriliş tarihi
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="date")
    private LocalDate date;

    //tahmini teslim tarihi
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="estimated_date")
    private LocalDate estimated_date;

    //teslim tarihi
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="delivery_date")
    private LocalDate delivery_date;

    //burası enum şeklinde yapılmalı
    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private double total_price;

    @JsonIgnoreProperties("user_order_list")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="order_product",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> productList;

    public Order(){

    }

    public Order(LocalDate date, LocalDate estimated_date, LocalDate delivery_date, String status, double total_price, User owner, List<Product> productList) {
        this.date = date;
        this.estimated_date = estimated_date;
        this.delivery_date = delivery_date;
        this.status = status;
        this.total_price = total_price;
        this.owner = owner;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getEstimated_date() {
        return estimated_date;
    }

    public void setEstimated_date(LocalDate estimated_date) {
        this.estimated_date = estimated_date;
    }

    public LocalDate getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDate delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
