package com.example.backendservicecore.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customer")
public class Customer extends UserData {
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Restaurant> restaurants;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @Builder(builderMethodName = "CustomerBuilder")
    public Customer(@lombok.NonNull String firstName, @lombok.NonNull String lastName, @lombok.NonNull String username, @lombok.NonNull String address, @NonNull String password) {
        super(firstName, lastName, username, password);
        this.address = address;
        this.orders = new HashSet<>();
    }
}
