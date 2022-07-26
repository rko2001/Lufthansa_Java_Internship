package com.example.backendservicecore.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "restaurantManager")
public class RestaurantManager extends UserData{

    @OneToMany(mappedBy = "restaurantManager", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Food> foods;


    @OneToMany(mappedBy = "restaurantManager", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<FoodCategory> foodCategories;


    @OneToMany(mappedBy = "restaurantManager", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Order> orders;

    @Builder(builderMethodName = "RestaurantManagerBuilder")
    public RestaurantManager(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password) {
        super(firstName, lastName, username, password);
    }



}
