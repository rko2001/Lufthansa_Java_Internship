package com.example.backendservicecore.repository;

import com.example.backendservicecore.model.Food;
import com.example.backendservicecore.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository<Food,Integer> {

    Optional<Food> findByNameAndRestaurant(String name, Restaurant restaurant);

    List<Food> findByRestaurant(Restaurant restaurant);

    List<Food> findAll();
}
