package com.example.backendservicecore.model.dto;

import com.example.backendservicecore.model.FoodCategory;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FoodDTO {
    @NonNull
    private String name;
    @NonNull
    private Integer price;
    @NonNull
    private String description;
    @NonNull
    private FoodCategory category;
    @NonNull
    private RestaurantDTO restaurantDTO;
}