package com.system.FoodDeliveryService.Service;

import com.system.FoodDeliveryService.Model.Customer;
import com.system.FoodDeliveryService.Model.Orders;
import com.system.FoodDeliveryService.Model.ServiceMode;
import com.system.FoodDeliveryService.Repository.ICustomerRepo;
import com.system.FoodDeliveryService.Repository.IOrdersRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService{
    @Autowired
    IOrdersRepo orderRepo;

    @Autowired
    ICustomerRepo customerRepo;

    //CreateOrderService
    public Orders createOrder(String email, String orderData){
        JSONObject obj = new JSONObject(orderData);

        Customer oldCustomer = customerRepo.findByEmail(email);
        if(oldCustomer == null){
            throw new IllegalStateException("Customer not found!!");
        }

        //If not null
        Orders oldOrder = orderRepo.findByOrderName(obj.getString("orderName"));
        if(oldOrder != null){
            throw new IllegalStateException("Order is already exists!!");
        }

        Orders newOrder = new Orders();
        if(obj.getString("customerDetails").equalsIgnoreCase(oldCustomer.getName())){
            newOrder.setCustomerDetails(oldCustomer.toString());
        }
        newOrder.setOrderName(obj.getString("orderName"));
        newOrder.setFoodMenu(obj.getString("foodMenu"));
        newOrder.setRestaurantName(obj.getString("restaurantName"));
        newOrder.setPaymentMode(obj.getString("paymentMode"));
        if(oldCustomer.getSubscription()){
            newOrder.setServiceMode(ServiceMode.valueOf("membership_subscription"));
        }
        orderRepo.save(newOrder);
        return newOrder;
    }

    //GetOrderById
    public Orders getOrderById(Integer id){
        Orders oldOrder = orderRepo.findById(id).get();
        if(oldOrder == null){
            throw new IllegalStateException("Order not found!!");
        }
        return oldOrder;
    }

    //Update Order By Restaurant and foodMenu
    public Orders updateOrder(String orderData){
        JSONObject obj = new JSONObject(orderData);
        Integer newOrders = obj.getInt("orderId");
        Orders oldOrder = orderRepo.findById(newOrders).get();

        if(oldOrder == null){
            throw new IllegalStateException("order not found!!");
        }
        oldOrder.setRestaurantName(obj.getString("name"));
        oldOrder.setFoodMenu(obj.getString("food"));
        orderRepo.save(oldOrder);
        return oldOrder;
    }
}


