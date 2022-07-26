package com.example.backendservicecore.repository;

import com.example.backendservicecore.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepo extends JpaRepository<FoodCategory, Integer> {

}
