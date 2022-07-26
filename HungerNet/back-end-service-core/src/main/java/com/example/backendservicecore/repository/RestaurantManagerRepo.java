package com.example.backendservicecore.repository;

import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.RestaurantManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantManagerRepo extends JpaRepository<RestaurantManager,Integer> {
    Optional<RestaurantManager> findByUsername(String username);
}
