package com.galaksiya.demoProject.dto;

import com.galaksiya.demoProject.entity.Order;
import com.galaksiya.demoProject.entity.Product;

import java.util.List;

public class OrderSaveDto {
    private List<Product> productList;

    public OrderSaveDto(){

    }

    public OrderSaveDto(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Order createOrderFromOrderSaveDto(){
        Order order= new Order();
        order.setProductList(this.getProductList());
        return order;
    }
}
