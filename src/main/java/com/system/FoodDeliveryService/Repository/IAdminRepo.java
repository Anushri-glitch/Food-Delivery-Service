package com.system.FoodDeliveryService.Repository;

import com.system.FoodDeliveryService.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,String> {
    Admin findByEmail(String adminMail);
}

