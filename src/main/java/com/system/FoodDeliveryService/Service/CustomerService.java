package com.system.FoodDeliveryService.Service;

import com.system.FoodDeliveryService.Model.Customer;
import com.system.FoodDeliveryService.Model.SignIn;
import com.system.FoodDeliveryService.Repository.ICustomerRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    ICustomerRepo customerRepo;

    //Create Customer Service
    public Customer createCustomer(String user) {
        JSONObject obj = new JSONObject(user);
        Customer oldUser = customerRepo.findByName(obj.getString("name"));

        if(oldUser != null){
            throw new IllegalStateException("User Already Registered!!");
        } else {
            Customer newUser = new Customer();
            newUser.setName(obj.getString("name"));
            newUser.setAddress(obj.getString("address"));
            newUser.setEmail(obj.getString("email"));
            newUser.setContact(obj.getString("contact"));
            newUser.setPassword(obj.getString("password"));
            newUser.setSubscription(false);
            newUser.setSubsDate("");
            newUser.setSubsType("");
            customerRepo.save(newUser);
            return newUser;
        }
    }

    //Update Name
    public Customer updateName(String data) {
        JSONObject obj = new JSONObject(data);
        Customer oldUser = customerRepo.findByName(obj.getString("oldName"));

        if(oldUser == null){
            throw new IllegalStateException("Customer Not Found");
        }
        Customer backupUser = oldUser;
        customerRepo.delete(oldUser);

        //Recreate User
        backupUser.setName(obj.getString("newName"));
        customerRepo.save(backupUser);
        return backupUser;
    }

    //Update Address
    public Customer updateAddress(String add) {
        JSONObject obj = new JSONObject(add);
        Customer oldUser = customerRepo.findByName(obj.getString("name"));

        if(oldUser == null){
            throw new IllegalStateException("Customer Not Found");
        }
        oldUser.setAddress(obj.getString("address"));
        customerRepo.save(oldUser);
        return oldUser;
    }

    //Update Contact Details Service
    public Customer updateContact(String contact) {
        JSONObject obj = new JSONObject(contact);
        Customer oldUser = customerRepo.findByName(obj.getString("name"));

        if(oldUser == null){
            throw new IllegalStateException("Customer Not Found");
        }
        oldUser.setAddress(obj.getString("contact"));
        customerRepo.save(oldUser);
        return oldUser;
    }

    //Update Email Service
    public Customer updateEmail(String email) {
        JSONObject obj = new JSONObject(email);
        Customer oldUser = customerRepo.findByName(obj.getString("name"));

        if(oldUser == null){
            throw new IllegalStateException("Customer Not Found");
        }
        oldUser.setAddress(obj.getString("email"));
        customerRepo.save(oldUser);
        return oldUser;
    }

    //Update Password Service
    public Customer updatePassword(String password) {
        JSONObject obj = new JSONObject(password);
        Customer oldUser = customerRepo.findByName(obj.getString("name"));

        if(oldUser == null){
            throw new IllegalStateException("Customer Not Found");
        }
        oldUser.setAddress(obj.getString("password"));
        customerRepo.save(oldUser);
        return oldUser;
    }

    //SignIn
    public Customer signIn(SignIn signInData) {
        Customer old = customerRepo.findByEmail(signInData.getEmail());

        if(old == null){
            throw new IllegalStateException("Customer not found!!");
        }
        return old;
    }
}
