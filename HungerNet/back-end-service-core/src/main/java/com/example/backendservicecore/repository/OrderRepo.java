package com.example.backendservicecore.repository;

import com.example.backendservicecore.model.Customer;
import com.example.backendservicecore.model.Food;
import com.example.backendservicecore.model.Order;
import com.example.backendservicecore.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    Optional<List<Order>> findAllByCustomer(Customer customer);

    Optional<List<Order>> findAllByRestaurant(Restaurant restaurant);
    List<Order> findByRestaurant(Restaurant restaurant);
}
