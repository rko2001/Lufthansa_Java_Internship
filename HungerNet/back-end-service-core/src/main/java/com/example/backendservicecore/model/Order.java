package com.example.backendservicecore.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orderh")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_order", updatable = false, unique = true, nullable = false)
    private Integer idOrder;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name = "total", nullable = false)
    private Integer total;


    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant restaurant;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_restaurantManager")
    private RestaurantManager restaurantManager;


    @NonNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "food_order",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_food"))
    private List<Food> foods;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
