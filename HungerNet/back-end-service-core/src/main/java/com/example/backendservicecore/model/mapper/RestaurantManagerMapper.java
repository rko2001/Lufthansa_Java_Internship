package com.example.backendservicecore.model.mapper;

import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.Customer;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.RestaurantManager;
import com.example.backendservicecore.model.dto.AdminDTO;
import com.example.backendservicecore.model.dto.CustomerDTO;
import com.example.backendservicecore.model.dto.RestaurantManagerDTO;

public class RestaurantManagerMapper implements Mapper<RestaurantManager,RestaurantManagerDTO>{

    private static RestaurantManagerMapper restaurantManagerMapper = null;

    private RestaurantManagerMapper() { }

    public static RestaurantManagerMapper getInstance() {
        if (restaurantManagerMapper == null) {
            restaurantManagerMapper = new RestaurantManagerMapper();
        }
        return restaurantManagerMapper;
    }

    @Override
    public RestaurantManager convertFromDTO(RestaurantManagerDTO restaurantManagerDTO) {
        return RestaurantManager.RestaurantManagerBuilder()
                .username(restaurantManagerDTO.getUsername())
                .firstName(restaurantManagerDTO.getFirstName())
                .lastName(restaurantManagerDTO.getLastName())
                .password(restaurantManagerDTO.getPassword())
                .build();
    }

    @Override
    public RestaurantManagerDTO convertToDTO(RestaurantManager restaurantManager) {
        return RestaurantManagerDTO.RestaurantManagerDtoBuilder()
                .firstName(restaurantManager.getFirstName())
                .lastName(restaurantManager.getLastName())
                .username(restaurantManager.getUsername())
                .password(restaurantManager.getPassword())
                .build();
    }
}
