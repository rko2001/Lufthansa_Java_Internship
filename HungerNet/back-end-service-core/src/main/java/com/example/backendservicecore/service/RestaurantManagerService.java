package com.example.backendservicecore.service;

import com.example.backendservicecore.model.RestaurantManager;
import com.example.backendservicecore.repository.RestaurantManagerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantManagerService {
    @Autowired
    private RestaurantManagerRepo restaurantManagerRepo;

    private final static Logger logger = LoggerFactory.getLogger(RestaurantManagerService.class.getName());


    /**
     * Saves in the database
     * @param  restaurantManager instance to be saved
     * @return the saved administrator
     */
    public RestaurantManager save(RestaurantManager restaurantManager) {
        logger.info("RestaurantManager {} saved to database", restaurantManager);
        RestaurantManager a = RestaurantManager.RestaurantManagerBuilder()
                .username(restaurantManager.getUsername())
                .firstName(restaurantManager.getFirstName())
                .lastName(restaurantManager.getLastName())
                .password(restaurantManager.getPassword())
                .build();
        return restaurantManagerRepo.save(a);
    }


    /**
     * Searches for an admin from the database by username
     * @param username of the admin
     * @return the admin
     */
    public RestaurantManager findByUsername(String username) {
        logger.info("Retrieve administrator: {} from the database", username);

        Optional<RestaurantManager> restaurantManager = restaurantManagerRepo.findByUsername(username);

        return restaurantManager.orElse(null);
    }
}
