package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.FoodMenu;
import com.system.FoodDeliveryService.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    //Create Food Menu
    @PostMapping(value = "/createMenu")
    public String createMenu(@RequestBody FoodMenu menus){
        return menuService.createMenu(menus);
    }

    //Delete Menu
    @DeleteMapping(value = "/deleteMenu/{name}")
    public String deleteMenu(@PathVariable String name){
        return menuService.deleteMenu(name);
    }
}
