package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.Customer;
import com.system.FoodDeliveryService.Model.SignIn;
import com.system.FoodDeliveryService.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //Create Customer
    @PostMapping(value = "/createCustomer")
    public Customer createCustomer(@RequestBody String user){
        return customerService.createCustomer(user);
    }

    //SignIn
    @PostMapping(value = "/signIn")
    public Customer signIn(@RequestBody SignIn signInData){
        return customerService.signIn(signInData);
    }

    //Update Customer name
    @PostMapping(value = "/updateName")
    public Customer updateName(@RequestBody String Data){
        return customerService.updateName(Data);
    }

    //Update Customer Address
    @PostMapping(value = "/updateAddress")
    public Customer updateAddress(@RequestBody String add){
        return customerService.updateAddress(add);
    }

    //Update Customer Contact
    @PostMapping(value = "/updateContact")
    public Customer updateContact(@RequestBody String contact){
        return customerService.updateContact(contact);
    }

    //Update Email
    @PostMapping(value = "/updateEmail")
    public Customer updateEmail(@RequestBody String email){
        return customerService.updateEmail(email);
    }

    //Update Password
    @PostMapping(value = "/updatePassword")
    public Customer updatePassword(@RequestBody String password){
        return customerService.updatePassword(password);
    }
}
