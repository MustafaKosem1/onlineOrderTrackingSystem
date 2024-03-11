package com.galaksiya.demoProject.repository;

import com.galaksiya.demoProject.entity.Order;

import java.util.List;

public interface IOrderRepository {

    void save(Order order);

    Order getById(int id);

    List<Order> getAll();

    void update(Order order);

    void delete(int id);
}
