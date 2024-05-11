package com.system.FoodDeliveryService.Service;

import com.system.FoodDeliveryService.Model.Restaurant;
import com.system.FoodDeliveryService.Repository.RestroRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestroService {
    @Autowired
    RestroRepo restroRepo;

    //Create Restaurent
    public Restaurant createRestro(Restaurant res) {

        Restaurant oldRestro = restroRepo.findByName(res.getName());
        if(oldRestro != null){
            throw new IllegalStateException("Restaurent Already Registered!!");
        }
        else{
            restroRepo.save(res);
            return oldRestro;
        }
    }

    //Update Restaurant Name
    public Restaurant updateRestroName(String nameData) {
        JSONObject jObject = new JSONObject(nameData);
        Restaurant oldRestro = restroRepo.findByName(jObject.getString("oldName"));

        if(oldRestro == null){
            throw new IllegalStateException("Please Register Restaurant!!");
        }
        else{
            Restaurant backupObj = oldRestro;
            String res = jObject.getString("newName");
            //System.out.println(res);
            //...First We delete the entity because we cannot update primary key directly
            restroRepo.delete(oldRestro);

            //Recreate the previous restaurant by new Name...
            backupObj.setName(res);
            return createRestro(backupObj);
        }
    }

    //Update Restaurant Location
    public String updateRestroLocation(String locData) {
        JSONObject jObject = new JSONObject(locData);
        Restaurant oldRestro = restroRepo.findByName(jObject.getString("name"));
        if(oldRestro == null){
            throw new IllegalStateException("Please Register Restaurant!!");
        }
        else{
            oldRestro.setLocation(jObject.getString("location"));
            restroRepo.save(oldRestro);
            return "Location " + jObject.getString("location") + " is Updated!!";
        }
    }

    public String deleteRestroByName(String name) {
        Restaurant oldRestro = restroRepo.findByName(name);
        if(oldRestro == null){
            throw new IllegalStateException("Restaurant not found!!");
        }
        restroRepo.delete(oldRestro);
        return "Restaurant " + oldRestro.getName() + " is Deleted Successfully!!";
    }
}
