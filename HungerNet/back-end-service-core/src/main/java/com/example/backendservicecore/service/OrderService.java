package com.example.backendservicecore.service;

import com.example.backendservicecore.model.*;
import com.example.backendservicecore.model.dto.OrderDTO;
import com.example.backendservicecore.model.mapper.OrderMapper;
import com.example.backendservicecore.repository.OrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CustomerService customerService;

    private final static Logger logger = LoggerFactory.getLogger(OrderService.class.getName());



     List<OrderDTO> findByRestaurant(String restaurantName){
        Restaurant restaurant = restaurantService.findByName(restaurantName);
        Optional<List<Order>> orders = Optional.ofNullable(orderRepo.findByRestaurant(restaurant));
        logger.info("Find orders by restaurant: {}", restaurantName);
        return orders.map(orderList -> orderList.stream()
                .map(OrderMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }



    /**
     * Retrieves all orders.
     * @return A List containing all the retrieved orders
     */
    public List<OrderDTO> findAll(){
        Optional<List<Order>> orders = Optional.ofNullable(orderRepo.findAll());
        logger.info("Find all orders");

        return orders.map(orderList -> orderList.stream()
                .map(OrderMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }


    /**
     * Saves a order to the database.
     * @param orderDTO containing the details of the order
     * @return The saved instance of the food item.
     */
    public OrderDTO save(OrderDTO orderDTO){
        logger.info("Save order {} to database", orderDTO.getIdOrder());
        Customer customer = customerService.findByUsername(orderDTO.getCustomer().getUsername());

        Order order = Order.builder()
                .idOrder(orderDTO.getIdOrder())
                .status(orderDTO.getStatus())
                .customer(customer)
                .build();

        return OrderMapper.getInstance().convertToDTO(orderRepo.save(order));
    }


    public Order updateOrder(Order order){
        Order existingProduct= orderRepo.findById(order.getIdOrder()).orElse(null);
        existingProduct.setIdOrder(order.getIdOrder());
        existingProduct.setCustomer(order.getCustomer());
        existingProduct.setStatus(order.getStatus());
        existingProduct.setDeliveryAddress(order.getDeliveryAddress());
        return orderRepo.save(existingProduct);
    }


    public String deleteOrder(int id){
        orderRepo.deleteById(id);
        return "order removed !!" +id;
    }


}

