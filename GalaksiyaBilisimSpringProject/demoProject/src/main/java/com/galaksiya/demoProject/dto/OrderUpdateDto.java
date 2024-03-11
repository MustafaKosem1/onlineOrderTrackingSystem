package com.galaksiya.demoProject.dto;

import com.galaksiya.demoProject.entity.Order;

import java.time.LocalDate;

public class OrderUpdateDto {
    private int id;

    private LocalDate delivery_date;

    private String status;

    public OrderUpdateDto(){}

    public OrderUpdateDto(int id, LocalDate delivery_date, String status) {
        this.id = id;
        this.delivery_date = delivery_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Order createOrderFromOrderUpdateDto(){
        Order order= new Order();
        order.setStatus(this.status);
        order.setDelivery_date(this.delivery_date);
        order.setId(this.id);
        return order;
    }
}
