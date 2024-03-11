package com.galaksiya.demoProject.business;

import com.galaksiya.demoProject.entity.Order;
import com.galaksiya.demoProject.entity.Product;
import com.galaksiya.demoProject.exception.NotEnoughStockException;
import com.galaksiya.demoProject.exception.OrderNotFoundException;
import com.galaksiya.demoProject.exception.ProductNotFoundException;
import com.galaksiya.demoProject.repository.IOrderRepository;
import com.galaksiya.demoProject.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
public class OrderService implements IOrderService{

    private IOrderRepository orderRepository;

    private IProductRepository productRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository, IProductRepository productRepository) {
        this.productRepository=productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void save(Order order){
        //delivery time'ı en uzun olan orderı bulmak için değişken
        int max_product_delivery_time=0;
        //şu anki tarih order tarihine atanıyor
        order.setDate(LocalDate.now());
        if(order.getProductList().isEmpty()){
            throw new ProductNotFoundException("No product in order. At least one product should be in order.");
        }
        //order edilen ürünlerin kendileri ve stockları var mı diye kontrol ediliyor
        for(Product product: order.getProductList()){
            Product product1=productRepository.getById(product.getId());
            if(product1==null || !(product1.getStock()>0)){
                throw new ProductNotFoundException("Product with id " + product.getId() + " not found in the database");
            }
            order.setTotal_price(order.getTotal_price()+product1.getPrice());
            if(product1.getDelivery_time()>max_product_delivery_time){
                max_product_delivery_time=product1.getDelivery_time();
            }
        }
        //stock yeterli mi kontrol ederek update ediyor
        try {
            productRepository.updateStock(order.getProductList());
        }
        catch (NotEnoughStockException e){
            throw e;
        }
        //tahmini teslim tarihini orderdaki en uzun beklenecek producta göre ayarlıyor
        order.setEstimated_date(order.getDate().plusDays(max_product_delivery_time));
        orderRepository.save(order);
    }

    @Override
    public Order getById(int id) {
        //çekilmek istene order var mı kontrolü
        if(orderRepository.getById(id)!=null){
            return orderRepository.getById(id);
        }
        else{
            throw new OrderNotFoundException("Order not found.");
        }
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public void update(Order order) {
        //update edilecek order gerçekten var mı kontrol
        if(orderRepository.getById(order.getId())!=null){
            Order updated_order=orderRepository.getById(order.getId());
            updated_order.setStatus(order.getStatus());
            updated_order.setDelivery_date(order.getDelivery_date());
            orderRepository.update(updated_order);
        }
        else{
            throw new OrderNotFoundException("Order not found.");
        }
    }

    @Override
    public void delete(int id) {
        Order order=orderRepository.getById(id);
        //silinecek order gerçekten var mı diye kontrol
        if(order!=null){
            for(Product product: order.getProductList()){
                product.setStock(product.getStock()+1);
                productRepository.update(product);
            }
            orderRepository.delete(id);
        }
        else{
            throw new OrderNotFoundException("Order not found.");
        }
    }

    //belli bir kullanıcın orderlarına erişen metot
    @Override
    public List<Order> getMyOrders(int id){
        return filterOrdersByUser(orderRepository.getAll(), id);
    }

    //orderı belli bir usera göre filtreleyen metot
    private List<Order> filterOrdersByUser(List<Order> orders, int id) {
        return orders.stream()
                .filter(order -> order.getOwner().getId() == id)
                .toList();
    }
}
