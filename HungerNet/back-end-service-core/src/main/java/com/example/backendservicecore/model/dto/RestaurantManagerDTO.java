package com.example.backendservicecore.model.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class RestaurantManagerDTO extends UserDataDTO{


    @Builder(builderMethodName = "RestaurantManagerDtoBuilder")
    public RestaurantManagerDTO(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password) {
        super(firstName, lastName, username, password);

    }
}
