package com.system.FoodDeliveryService.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer orderId;
    private String orderName; //name of customer
    private String restaurantName;
    private String foodMenu;
    private String paymentMode;
    private ServiceMode serviceMode;
    private String customerDetails;
    private String paymentReceipt;
    private String revenueReport;
    private Boolean isDelivered;
    private Boolean isPaymentDone;
}

