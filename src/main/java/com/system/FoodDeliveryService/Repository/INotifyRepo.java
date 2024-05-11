package com.system.FoodDeliveryService.Repository;

import com.system.FoodDeliveryService.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotifyRepo extends JpaRepository<Notification,String> {

    Notification findByNotificationName(String notifyName);
}

