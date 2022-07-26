package com.example.backendservicecore.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends UserData {

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Restaurant> restaurants;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Customer> customers;

    @Builder(builderMethodName = "AdminBuilder")
    public Admin(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password) {
        super(firstName, lastName, username, password);
    }
}


