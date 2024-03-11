package com.galaksiya.demoProject.business;

import com.galaksiya.demoProject.entity.Order;

import java.util.List;

public interface IOrderService {
    void save(Order order);

    Order getById(int id);

    List<Order> getAll();

    void update(Order order);

    void delete(int id);

    List<Order> getMyOrders(int id);
}
