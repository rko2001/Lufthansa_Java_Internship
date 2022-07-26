package com.example.backendservicecore.model.mapper;

import com.example.backendservicecore.model.dto.RestaurantDTO;
import com.example.backendservicecore.model.Restaurant;

import java.util.stream.Collectors;

public class RestaurantMapper implements Mapper<Restaurant, RestaurantDTO> {
    private static RestaurantMapper restaurantMapper = null;

    private RestaurantMapper() { }

    public static RestaurantMapper getInstance() {
        if (restaurantMapper == null) {
            restaurantMapper = new RestaurantMapper();
        }
        return restaurantMapper;
    }

    @Override
    public Restaurant convertFromDTO(RestaurantDTO restaurantDTO) {
        return null;
    }

    @Override
    public RestaurantDTO convertToDTO(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .adminDTO(AdminMapper.getInstance().convertToDTO(restaurant.getAdmin()))
                .build();
    }
}
