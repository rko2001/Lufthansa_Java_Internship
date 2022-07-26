package com.example.backendservicecore.repository;

import com.example.backendservicecore.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
    Optional<Restaurant> findByName(String name);
}
