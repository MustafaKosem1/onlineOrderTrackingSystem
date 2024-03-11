package com.galaksiya.demoProject.repository;

import com.galaksiya.demoProject.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class OrderRepository implements  IOrderRepository{

    private EntityManager entityManager;

    @Autowired
    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    public Order getById(int id) {
        return entityManager.find(Order.class,id);
    }

    @Override
    public List<Order> getAll() {
        TypedQuery<Order> typedQuery=entityManager.createQuery("select o from Order o", Order.class);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Order order= entityManager.find(Order.class, id);
        entityManager.remove(order);
    }
}
