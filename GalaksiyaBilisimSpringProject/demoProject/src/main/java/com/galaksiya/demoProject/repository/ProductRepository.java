package com.galaksiya.demoProject.repository;

import com.galaksiya.demoProject.entity.Product;
import com.galaksiya.demoProject.exception.NotEnoughStockException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class ProductRepository implements IProductRepository{

    private EntityManager entityManager;

    @Autowired
    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product getById(int id) {
        return entityManager.find(Product.class,id);
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> typedQuery= entityManager.createQuery("select p from Product p", Product.class);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    //satın alım yapıldığı zaman stock güncelleyen metot.
    public void updateStock(List<Product> productList){
        for(Product product: productList){
            Product product1=entityManager.find(Product.class,product.getId());
            if(product1.getStock()<=0){
                throw new NotEnoughStockException("Not enough stock for products.");
            }
            product1.setStock(product1.getStock()-1);
            entityManager.merge(product1);
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        Product product= entityManager.find(Product.class,id);
        entityManager.remove(product);
    }
}
