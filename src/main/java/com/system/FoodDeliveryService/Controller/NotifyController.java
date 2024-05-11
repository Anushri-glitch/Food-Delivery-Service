package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.Notification;
import com.system.FoodDeliveryService.Model.NotificationResponse;
import com.system.FoodDeliveryService.Model.Orders;
import com.system.FoodDeliveryService.Repository.INotifyRepo;
import com.system.FoodDeliveryService.Repository.IOrdersRepo;
import com.system.FoodDeliveryService.Service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyController{

    @Autowired
    NotifyService notifyService;

    //Notification Done
    @PostMapping(value = "/notificationDone/{orderId}")
    public Orders notificationDone(@PathVariable Integer orderId){
        return notifyService.notificationDone(orderId);
    }

    //Delivery Done
    @PostMapping(value = "/deliveryDone/{orderId}")
    public Orders deliveryDone(@PathVariable Integer orderId){
        return notifyService.deliveryDone(orderId);
    }

    //Create Notification for Restaurant
    @PostMapping(value="/createNotify/{orderId}")
    public Notification createNotify(@PathVariable Integer orderId){
        return notifyService.createNotify(orderId);
    }

    //Create Notification for Customer
    @PostMapping(value="/createNotifyForCustomer/{notifyName}")
    public NotificationResponse ResponseCreateForCustomer(@PathVariable String notifyName){
        return notifyService.createForCustomer(notifyName);
    }

    //If Restaurant Accept Request
    @PostMapping(value = "/status/{notifyName}")
    public Notification status(@PathVariable String notifyName){
        return notifyService.status(notifyName);
    }

    //If Restaurant Reject Request
    @PostMapping(value = "/refund/{notifyName}")
    public Notification refund(@PathVariable String notifyName){
        return notifyService.refund(notifyName);
    }
}

