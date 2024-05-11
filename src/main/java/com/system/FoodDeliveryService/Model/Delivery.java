package com.system.FoodDeliveryService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery{

    private String deliveryStatus;
    private String feedback;
}

