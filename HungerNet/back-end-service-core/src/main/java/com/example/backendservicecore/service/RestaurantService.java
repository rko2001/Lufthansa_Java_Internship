package com.example.backendservicecore.service;

import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.dto.RestaurantDTO;
import com.example.backendservicecore.model.mapper.RestaurantMapper;
import com.example.backendservicecore.repository.RestaurantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private AdminService adminService;


    private final static Logger logger = LoggerFactory.getLogger(RestaurantService.class.getName());


    /**
     * Retrieves a restaurant from the database by its name.
     * @param name of the restaurant
     * @return The found restaurant.
     */
    public Restaurant findByName(String name) {
        logger.info("Get restaurant with name {}", name);
        Optional<Restaurant> restaurant = restaurantRepo.findByName(name);
        return restaurant.orElse(null);
    }

    /**
     * Saves a new restaurant to the database.
     * @param restaurantDTO containing the details of the new restaurant
     * @return The saved restaurant instance.
     */
    public RestaurantDTO save(RestaurantDTO restaurantDTO){
        logger.info("Save restaurant {} to database", restaurantDTO.getName());
        Admin admin = adminService.findByUsername(restaurantDTO.getAdminDTO().getUsername());

        String address = restaurantDTO.getAddress();

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .address(address)
                .admin(admin)
                .build();

        return RestaurantMapper.getInstance().convertToDTO(restaurantRepo.save(restaurant));
    }


    /**
     * Retrieves all restaurants from the database.
     * @return A List containing all restaurants.
     */
    public List<RestaurantDTO> findAll() {
        logger.info("Find all restaurants from the database");
        return restaurantRepo.findAll().stream()
                .map(RestaurantMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }


    public Restaurant saveRestaurant(Restaurant restaurant){
        return   restaurantRepo.save(restaurant);
    }


    public List<Restaurant> saveRestaurants(List<Restaurant> restaurants){
        return   restaurantRepo.saveAll(restaurants);
    }


    public List<Restaurant> getRestaurants(){
        return restaurantRepo.findAll();
    }


    public Restaurant getRestaurantById(int id){
        return restaurantRepo.findById(id).orElse(null);
    }


    public String deleteRestaurant(int id){
        restaurantRepo.deleteById(id);
        return "restaurant removed !!" +id;
    }


    public Restaurant updateRestaurant(Restaurant restaurant){
        Restaurant existingProduct=restaurantRepo.findById(restaurant.getIdRestaurant()).orElse(null);
        existingProduct.setName(restaurant.getName());
        existingProduct.setAddress(restaurant.getAddress());
        existingProduct.setIdRestaurant(restaurant.getIdRestaurant());
        return restaurantRepo.save(existingProduct);
    }


}
