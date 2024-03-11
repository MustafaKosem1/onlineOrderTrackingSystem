package com.galaksiya.demoProject.business;

import com.galaksiya.demoProject.entity.Product;
import com.galaksiya.demoProject.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService{

    private IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    //stockta bulunan productları dönen metot
    @Override
    public List<Product> getAvailableProducts(String category, Double minPrice, Double maxPrice) {
        List<Product> allProducts= productRepository.getAll();
        List<Product> filteredProducts = filterProducts(allProducts, category, minPrice, maxPrice);
        return filteredProducts;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    //productları kategori veya price'a göre filtreleyen metot
    private List<Product> filterProducts(List<Product> products, String category, Double minPrice, Double maxPrice) {
        return products.stream()
                .filter(product -> category == null || product.getCategory().equals(category))
                .filter(product -> minPrice == null || product.getPrice() >= minPrice)
                .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
                .filter(product -> product.getStock() >0)
                .toList();
    }
}
