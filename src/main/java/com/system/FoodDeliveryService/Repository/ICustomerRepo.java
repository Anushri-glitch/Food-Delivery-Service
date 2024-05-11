package com.system.FoodDeliveryService.Repository;

import com.system.FoodDeliveryService.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, String> {
    Customer findByName(String name);

    Customer findByEmail(String email);
}
