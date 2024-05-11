package com.system.FoodDeliveryService.Repository;
import com.system.FoodDeliveryService.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepo extends JpaRepository<Orders, Integer> {
    Orders findByOrderName(String orderName);
}

