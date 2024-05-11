package com.system.FoodDeliveryService.Service;

import com.system.FoodDeliveryService.Model.FoodMenu;
import com.system.FoodDeliveryService.Repository.IMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    IMenuRepo menuRepo;

    //Create Menu Service
    public String createMenu(FoodMenu menus) {
        FoodMenu oldMenu = menuRepo.findByName(menus.getName());

        if(oldMenu == null){
            menuRepo.save(menus);
            return menus.getName() + " is Sucessfully created!!";
        }
        throw new IllegalStateException("Menu is Already Present!!");

    }

    //Delete Menu Service
    public String deleteMenu(String name) {
        FoodMenu oldMenu = menuRepo.findByName(name);
        if(oldMenu == null){
            throw new IllegalStateException("Menu not found");
        }
        menuRepo.delete(oldMenu);
        return "Menu Deleted!!";
    }

}
