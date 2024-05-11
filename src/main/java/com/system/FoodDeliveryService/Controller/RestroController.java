package com.system.FoodDeliveryService.Controller;

import com.system.FoodDeliveryService.Model.Restaurant;
import com.system.FoodDeliveryService.Repository.RestroRepo;
import com.system.FoodDeliveryService.Service.RestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestroController {
    @Autowired
    RestroService restroService;

    @Autowired
    RestroRepo restroRepo;

    //Create Restaurent
    @PostMapping(value = "/createRestro")
    public Restaurant createRestro(@RequestBody Restaurant res){
        return restroService.createRestro(res);
    }

    //All Restaurents
    @GetMapping(value = "/getAllRestro")
    public List<Restaurant> getAllRestro(){
        return restroRepo.findAll();
    }

    //Restaurents By Name
    @GetMapping(value = "/getRestroByName/{name}")
    public Restaurant getRestroByName(@PathVariable String name){
        return restroRepo.findByName(name);
    }

    //Restaurents By Location
    @GetMapping(value = "/getRestroByLocation/{location}")
    public Restaurant getRestroByLocation(@PathVariable String location){
        return restroRepo.findByLocation(location);
    }

    //Restaurents By Rating
    @GetMapping(value = "/getRestroByRating/{rating}")
    public Restaurant getRestroByRating(@PathVariable String rating){
        return restroRepo.findByRating(rating);
    }

    //Restaurent By Type
    @GetMapping(value = "/getRestroByType/{type}")
    public Restaurant getrestroByType(@PathVariable String type){
        return restroRepo.findByType(type);
    }

    //Update Restaurent Name
    @PostMapping(value = "/updateRestroName")
    public Restaurant updateRestro(@RequestBody String nameData){
        return restroService.updateRestroName(nameData);
    }

    //Update Restaurent Location
    @PostMapping(value = "/updateRestroLoc")
    public String updateRestroLoc(@RequestBody String locData){
        return restroService.updateRestroLocation(locData);
    }

    //Delete Restaurent
    @DeleteMapping(value = "/deleteRestro/{name}")
    public String deleteRestroByName(@PathVariable String name){
        return restroService.deleteRestroByName(name);
    }
}
