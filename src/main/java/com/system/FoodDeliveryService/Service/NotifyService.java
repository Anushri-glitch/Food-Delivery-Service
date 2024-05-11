package com.system.FoodDeliveryService.Service;

import com.system.FoodDeliveryService.Model.Delivery;
import com.system.FoodDeliveryService.Model.Notification;
import com.system.FoodDeliveryService.Model.NotificationResponse;
import com.system.FoodDeliveryService.Model.Orders;
import com.system.FoodDeliveryService.Repository.INotifyRepo;
import com.system.FoodDeliveryService.Repository.IOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotifyService{
    @Autowired
    INotifyRepo notifyRepo;

    @Autowired
    IOrdersRepo orderRepo;

    //True Some Conditions


    //Create Notification For Restaurant
    public Notification createNotify(Integer orderId){
        Orders newOrder = orderRepo.findById(orderId).get();

        if(newOrder == null){
            throw new IllegalStateException("Order Not found!!");
        }else{
            if(newOrder.getIsPaymentDone()){
                Notification notify = new Notification();
                notify.setNotificationName(newOrder.getRestaurantName());
                notify.setOrderId(newOrder.getOrderId());
                notifyRepo.save(notify);
                return notify;
            }
            throw new IllegalStateException("Payment is not done!!");
        }
    }

    //Create For Customer
    public NotificationResponse createForCustomer(String notifyName){
        Notification newForCustomer= notifyRepo.findByNotificationName(notifyName);
        if(newForCustomer == null){
            throw new IllegalStateException("Please Generate Notification!!");
        }
        NotificationResponse newResponse = new NotificationResponse();
        newResponse.setOrderId(newForCustomer.getOrderId());
        if(newForCustomer.getStatus() == null){
            newResponse.setOrderStatus("accepted");
            return newResponse;
        }
        else if(!newForCustomer.getStatus() && newForCustomer.getRefund()){
            newResponse.setOrderStatus("rejected");
            newResponse.setRefund(true);
            return newResponse;
        }
        return null;
    }

    //CreateDelivery
    public Delivery createDelivery(String notifyName){
        Notification oldOne = notifyRepo.findByNotificationName(notifyName);
        if(oldOne == null){
            throw new IllegalStateException("Please generate notification!!");
        }else{
            Delivery deliver = new Delivery();
            Optional<Orders> currentOrderOptional = orderRepo.findById(oldOne.getOrderId());
            if (currentOrderOptional.isPresent()) {
                Orders currentOrder = currentOrderOptional.get();
                if (currentOrder.getIsDelivered()) {
                    deliver.setDeliveryStatus("Delivery Successful!! Please give feedback");
                    return deliver;
                }
            }
        }
        return null;
    }


    //Notification Done
    public Orders notificationDone(Integer orderId) {
        Orders oldOrder = orderRepo.findById(orderId).get();

        if(oldOrder != null && oldOrder.getIsPaymentDone() == null){
            oldOrder.setIsPaymentDone(true);
            oldOrder.setPaymentReceipt("receipt.pdf");
            orderRepo.save(oldOrder);
            return oldOrder;
        }
        throw new IllegalStateException("Order not found!!");
    }

    //Delivery Done
    public Orders deliveryDone(Integer orderId) {
        Orders oldOrder = orderRepo.findById(orderId).get();

        if(oldOrder != null && oldOrder.getIsDelivered() == null){
            oldOrder.setIsDelivered(true);
            orderRepo.save(oldOrder);
            return oldOrder;
        }
        throw new IllegalStateException("Order not found!!");
    }

    //If Restaurant Accept Request
    public Notification status(String notifyName) {

        Notification response = notifyRepo.findByNotificationName(notifyName);
        if(response != null && response.getStatus() == null){
            response.setStatus(true);
            response.setRefund(false);
            notifyRepo.save(response);
        }
        throw new IllegalStateException("Generate Notification!!");
    }

    public Notification refund(String notifyName) {
        Notification response = notifyRepo.findByNotificationName(notifyName);
        if(response != null && response.getStatus() == null){
            response.setStatus(false);
            response.setRefund(true);
            notifyRepo.save(response);
        }
        throw new IllegalStateException("Generate Notification!!");
    }
}


