package com.example.backendservicecore.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RestaurantDTO {

    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private AdminDTO adminDTO;
}
