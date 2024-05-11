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
public class Restaurant {

    @Id
    private String name;
    private String location;
    private String rating;
    private String menu;
    private String type;
    private Category category;
}
