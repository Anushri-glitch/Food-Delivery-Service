package com.system.FoodDeliveryService.Service;

import com.system.FoodDeliveryService.Model.Admin;
import com.system.FoodDeliveryService.Model.Customer;
import com.system.FoodDeliveryService.Model.Subscription;
import com.system.FoodDeliveryService.Repository.IAdminRepo;
import com.system.FoodDeliveryService.Repository.ICustomerRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AdminService{
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    ICustomerRepo customerRepo;

    //CreateAdmin
    public Admin signUp(Admin adminData){
        adminRepo.save(adminData);
        return adminData;
    }

    //Update Customer Subscription By Admin
    public Customer updateSubscription(String email) {
        JSONObject obj = new JSONObject(email);
        Admin myAdmin = adminRepo.findByEmail(obj.getString("adminMail"));
        if (myAdmin != null) {
            Customer user = customerRepo.findByEmail(obj.getString("email"));

            if (user == null) {
                throw new IllegalStateException("Customer not found!!");
            } else {
                if (user.getSubscription()) {
                    LocalDate current = LocalDate.now();
                    LocalDate applyDate = LocalDate.parse(user.getSubsDate());
                    Long daysBetween = ChronoUnit.DAYS.between(applyDate, current);

                    if (daysBetween > 30 && user.getSubsType().toString().equals("monthly")) {
                        //It means subscription is over
                        user.setSubscription(false);
                        customerRepo.save(user);
                        return user;
                    } else if ((daysBetween > 365 || daysBetween > 366) && user.getSubsType().toString().equals("annually")) {
                        //Itmeanssubscriptioncrossedtheyearandrenewalnotdone
                        user.setSubscription(false);
                        customerRepo.save(user);
                        return user;
                    } else {
                        throw new IllegalStateException("Dont Need Update!!");
                    }
                } else {
                    throw new IllegalStateException("Customer Doesn't Have Subscription!!");
                }
            }
        }
        throw new IllegalStateException("Illegal Action!!");
    }

    //Create Membership Subscription Service
    public Customer createSubscription(String email, String adminMail, Subscription subs){
        Admin oldAdmin = adminRepo.findByEmail(adminMail);
        if(oldAdmin != null){
            Customer user= customerRepo.findByEmail(email);

            if(user == null){
                throw new IllegalStateException("Customer Not found");
            }
            else{
                if(!user.getSubscription() && subs.getIsInterested()){
                    user.setSubscription(true);
                    user.setSubsDate(subs.getSubscribeDate());
                    user.setSubsType(subs.getSubscriptionType().toString());
                    customerRepo.save(user);
                    return user;
                }
                else{
                    throw new IllegalStateException("Customer Not Interested!!");
                }
            }
        }
        throw new IllegalStateException("Illegal Action!!");
    }
}

