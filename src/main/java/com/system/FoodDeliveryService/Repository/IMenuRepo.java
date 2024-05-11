package com.system.FoodDeliveryService.Repository;

import com.system.FoodDeliveryService.Model.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuRepo extends JpaRepository<FoodMenu, String> {
    FoodMenu findByName(String name);
}
