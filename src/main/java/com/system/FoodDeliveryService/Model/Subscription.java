package com.system.FoodDeliveryService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private Boolean isInterested;
    private String subscribeDate;
    private SubsType subscriptionType;
}
