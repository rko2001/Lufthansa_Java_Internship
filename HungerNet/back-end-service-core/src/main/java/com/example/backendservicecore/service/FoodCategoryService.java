package com.example.backendservicecore.service;

import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.FoodCategory;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.dto.RestaurantDTO;
import com.example.backendservicecore.model.mapper.RestaurantMapper;
import com.example.backendservicecore.repository.FoodCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepo foodCategoryRepo;


    /**
     * Retrieves all food categories.
     * @return A list containing all retrieved food categories.
     */
    public List<FoodCategory> findAll() {
        List<FoodCategory> foods = foodCategoryRepo.findAll();
        for (FoodCategory foodCat :
                foods) {
            System.out.println(foodCat);
        }
        return foods;
    }



    public FoodCategory updateFoodCategory(FoodCategory foodCategory){
        FoodCategory existingProduct= foodCategoryRepo.findById(foodCategory.getIdCategory()).orElse(null);
        existingProduct.setName(foodCategory.getName());
        existingProduct.setFoods(foodCategory.getFoods());
        existingProduct.setRestaurantManager(foodCategory.getRestaurantManager());
        return foodCategoryRepo.save(existingProduct);
    }


    public String deleteFoodCategory(int id){
        foodCategoryRepo.deleteById(id);
        return "Food Category removed !!" +id;
    }


    public List<FoodCategory> saveFoodCategorys(List<FoodCategory> foodCategories){
        return   foodCategoryRepo.saveAll(foodCategories);
    }



}
