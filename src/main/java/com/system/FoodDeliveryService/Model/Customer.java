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
public class Customer {

    @Id
    private String name;
    private String address;
    private String email;
    private String contact;
    private String password;
    private Boolean subscription;
    private String subsDate;
    private String subsType;

}
