package com.system.FoodDeliveryService.Repository;

import com.system.FoodDeliveryService.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestroRepo extends JpaRepository<Restaurant, String> {
    Restaurant findByName(String name);

    Restaurant findByLocation(String location);

    Restaurant findByRating(String rating);

    Restaurant findByType(String type);
}
