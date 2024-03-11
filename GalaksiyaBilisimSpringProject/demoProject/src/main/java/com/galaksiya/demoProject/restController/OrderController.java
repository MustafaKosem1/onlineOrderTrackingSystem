package com.galaksiya.demoProject.restController;

import com.galaksiya.demoProject.business.IOrderService;
import com.galaksiya.demoProject.business.IUserService;
import com.galaksiya.demoProject.dto.OrderSaveDto;
import com.galaksiya.demoProject.dto.OrderUpdateDto;
import com.galaksiya.demoProject.entity.Order;
import com.galaksiya.demoProject.entity.User;
import com.galaksiya.demoProject.exception.NotEnoughStockException;
import com.galaksiya.demoProject.exception.OrderNotFoundException;
import com.galaksiya.demoProject.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private IOrderService orderService;

    private IUserService userService;

    @Autowired
    public OrderController(IOrderService orderService, IUserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    //@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER')")
    @Operation(summary = "Order oluşturma alanı.", description = "Giriş yapılan kullanıcıya order ekler.")
    @PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody OrderSaveDto orderSaveDto){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUserName(authentication.getName());
        Order order=orderSaveDto.createOrderFromOrderSaveDto();
        order.setOwner(user);
        order.setStatus("Paketleme aşamasında.");
        try {
            orderService.save(order);
        }
        catch (ProductNotFoundException | NotEnoughStockException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @Operation(summary = "Kullanıcının kendi order'larını göreceği alan.", description = "Giriş yapan kişinin order'ları.")
    @GetMapping("/find/myOrders")
    public ResponseEntity<List<Order>> getMyOrders(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        int id = userService.getByUserName(authentication.getName()).getId();
        List<Order> orderList= orderService.getMyOrders(id);
        return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
    }


    @Operation(summary = "Id'sine göre order çekme.")
    @GetMapping("/find/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable int id){
        try {
            Order order= orderService.getById(id);
            return new ResponseEntity<Order>(order,HttpStatus.OK);
        }
        catch (OrderNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Tüm order'lara erişim alanı.")
    @GetMapping("/find")
    public ResponseEntity<List<Order>> findAllOrder(){
        List<Order> orderList= orderService.getAll();
        return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
    }

    @Operation(summary = "Order update alanı.", description = "Sadece manager'ler update edebilir.")
    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody OrderUpdateDto orderUpdateDto){
        Order order=orderUpdateDto.createOrderFromOrderUpdateDto();
        try {
            orderService.update(order);
        }
        catch (OrderNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @Operation(summary = "Kullanıcının order'larını iptal edebileceği alan.")
    @GetMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable int id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUserName(authentication.getName());
        if(orderService.getById(id).getOwner().equals(user)){
            orderService.delete(id);
        }
        else {
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
