package com.system.FoodDeliveryService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification{

    @Id
    private String notificationName;
    private Integer orderId;
    private Boolean status;
    private Boolean refund;
}

