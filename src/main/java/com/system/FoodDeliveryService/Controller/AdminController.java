package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.Admin;
import com.system.FoodDeliveryService.Model.Customer;
import com.system.FoodDeliveryService.Model.Subscription;
import com.system.FoodDeliveryService.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    //Create Admin
    @PostMapping(value = "/createAdmin")
    public Admin signUp(@RequestBody Admin adminData){
        return adminService.signUp(adminData);
    }

    //Create Membership Subscription For Customer
    @PostMapping(value = "/createSubs/{email}/{adminMail}")
    public Customer createSubscription(@PathVariable String email, @PathVariable String adminMail, @RequestBody Subscription subs){
        return adminService.createSubscription(email, adminMail, subs);
    }

    //Update Membership Subscription
    @PostMapping(value = "/updateSubs")
    public Customer updateSubscription(@RequestBody String email){
        return adminService.updateSubscription(email);
    }
}

