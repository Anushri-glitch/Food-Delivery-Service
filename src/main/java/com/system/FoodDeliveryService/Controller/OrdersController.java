package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.Orders;
import com.system.FoodDeliveryService.Repository.IOrdersRepo;
import com.system.FoodDeliveryService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    OrderService orderService;

    @Autowired
    IOrdersRepo orderRepo;

    //CreateOrder
    @PostMapping(value="/createOrder/{name}")
    public Orders createOrder(@PathVariable String name, @RequestBody String orderData){
        return orderService.createOrder(name,orderData);
    }

    //GetOrderById
    @GetMapping(value="/getOrderById/{id}")
    public Orders getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }

    //GetAllOrders
    @GetMapping(value="/getAllOrders")
    public List<Orders> getAllOrders(){
        return orderRepo.findAll();
    }

    //deleteOrder
    @DeleteMapping(value="/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Integer id){
        if(orderRepo.findById(id) != null){
            orderRepo.delete(orderRepo.findById(id).get());
        }
        throw new IllegalStateException("Order not found!!");
    }

    //UpdateOrder
    @PostMapping(value="/updateOrder")
    public Orders updateOrder(@RequestBody String orderData){
        return orderService.updateOrder(orderData);
    }
}

