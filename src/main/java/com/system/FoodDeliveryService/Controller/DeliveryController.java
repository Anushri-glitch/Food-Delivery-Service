package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.Delivery;
import com.system.FoodDeliveryService.Service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController{

    @Autowired
    NotifyService notifyService;

    @PostMapping(value="/createDelivery/{notifyName}")
    public Delivery createDelivery(@PathVariable String notifyName){
        return notifyService.createDelivery(notifyName);
    }
}

