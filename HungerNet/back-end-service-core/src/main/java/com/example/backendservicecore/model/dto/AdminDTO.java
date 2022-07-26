package com.example.backendservicecore.model.dto;

import com.example.backendservicecore.model.Restaurant;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class AdminDTO extends UserDataDTO {
    private Restaurant restaurants;

    @Builder(builderMethodName = "AdminDTOBuilder")
    public AdminDTO(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, Restaurant restaurant) {
        super(firstName, lastName, username, password);
        this.restaurants = restaurant;
    }
}