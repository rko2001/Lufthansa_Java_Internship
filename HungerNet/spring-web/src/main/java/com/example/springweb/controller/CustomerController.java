package com.example.springweb.controller;

import com.example.backendservicecore.model.dto.CustomerDTO;
import com.example.backendservicecore.model.dto.FoodDTO;
import com.example.backendservicecore.model.dto.OrderDTO;
import com.example.backendservicecore.model.dto.RestaurantDTO;
import com.example.backendservicecore.model.mapper.CustomerMapper;
import com.example.backendservicecore.service.CustomerService;
import com.example.backendservicecore.service.FoodService;
import com.example.backendservicecore.service.OrderService;
import com.example.backendservicecore.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());


    @PostMapping("/registerCustomer")
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody(required = false) CustomerDTO customerDTO) {
        logger.info("Customer {} registered", customerDTO.getUsername());
        return new ResponseEntity<>(customerService.register(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getCurrentCustomer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDTO getCurrentCustomer(@Param("customerUsername") String customerUsername) {
        return CustomerMapper.getInstance().convertToDTO(customerService.findByUsername(customerUsername));
    }

    @GetMapping("/viewRestaurants")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<RestaurantDTO>> findRestaurants() {
        return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody(required = false) OrderDTO orderDTO) {
        logger.info("Add order {}", orderDTO.getIdOrder());
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.CREATED);
    }

    @GetMapping("/viewOrders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<OrderDTO>> getOrders() {
        System.out.println(orderService.findAll());
        return new ResponseEntity<>(orderService.findAll(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewMenu")
    public ResponseEntity<List<FoodDTO>> getFoods(){
        return new ResponseEntity<>(foodService.findAll(), HttpStatus.ACCEPTED);
    }




}
