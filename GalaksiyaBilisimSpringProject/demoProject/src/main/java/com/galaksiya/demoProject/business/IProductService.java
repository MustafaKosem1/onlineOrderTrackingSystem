package com.galaksiya.demoProject.business;

import com.galaksiya.demoProject.entity.Product;

import java.util.List;

public interface IProductService {

    void save(Product product);

    Product getById(int id);

    List<Product> getAvailableProducts(String category, Double minPrice, Double maxPrice);

    List<Product> getAll();

    void update(Product product);

    void delete(int id);
}
