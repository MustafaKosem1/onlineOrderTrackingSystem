package com.galaksiya.demoProject.repository;

import com.galaksiya.demoProject.entity.Product;

import java.util.List;

public interface IProductRepository {

    void save(Product product);

    Product getById(int id);

    List<Product> getAll();

    void update(Product product);

    void updateStock(List<Product> productList);

    void delete(int id);
}
