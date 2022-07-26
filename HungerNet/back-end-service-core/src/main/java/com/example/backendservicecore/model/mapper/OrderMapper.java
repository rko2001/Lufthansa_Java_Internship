package com.example.backendservicecore.model.mapper;

import com.example.backendservicecore.model.Order;
import com.example.backendservicecore.model.dto.OrderDTO;

import java.util.stream.Collectors;

public class OrderMapper implements Mapper<Order, OrderDTO> {
    private static OrderMapper orderMapper = null;

    private OrderMapper() { }

    public static OrderMapper getInstance() {
        if (orderMapper == null) {
            orderMapper = new OrderMapper();
        }
        return orderMapper;
    }

    @Override
    public Order convertFromDTO(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .idOrder(order.getIdOrder())
                .status(order.getStatus())
                .customer(CustomerMapper.getInstance().convertToDTO(order.getCustomer()))
                .foods(order.getFoods().stream()
                        .map(FoodMapper.getInstance()::convertToDTO)
                        .collect(Collectors.toList()))
                .restaurant(RestaurantMapper.getInstance().convertToDTO(order.getRestaurant()))
                .total(order.getTotal())
                .build();
    }
}
