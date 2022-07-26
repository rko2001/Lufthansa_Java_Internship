package com.example.springweb.controller;

import com.example.backendservicecore.model.Customer;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.RestaurantManager;
import com.example.backendservicecore.model.dto.*;
import com.example.backendservicecore.model.mapper.AdminMapper;
import com.example.backendservicecore.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService AdminService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantManagerService restaurantManagerService;

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class.getName());



    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AdminDTO getCurrentAdmin(@Param("adminUsername") String adminUsername) {
        logger.info("Obtain admin {} data.", adminUsername);
        return AdminMapper.getInstance().convertToDTO(AdminService.findByUsername(adminUsername));
    }

    @PostMapping("/addCustomer")  public Customer addCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PostMapping("/addCustomers")
    public List<Customer> addCustomers(@RequestBody List<Customer> customers){
        return customerService.saveCustomers(customers);
    }


    @GetMapping("/findAllCustomers")
    public List<Customer> findAllCustomers(){
        return  customerService.getCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }


    @PutMapping ("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }


    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody(required = false) RestaurantDTO restaurantDTO) {
        logger.info("Add restaurant {}", restaurantDTO.getName());
        return new ResponseEntity<>(restaurantService.save(restaurantDTO), HttpStatus.CREATED);
    }

    @PostMapping("/addRestaurants")
    public List<Restaurant> addRestaurants(@RequestBody List<Restaurant> restaurants){
        return restaurantService.saveRestaurants(restaurants);
    }


    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant(){
        return  restaurantService.getRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant findRestaurantById(@PathVariable int id){
        return restaurantService.getRestaurantById(id);
    }


    @PutMapping ("/update")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.updateRestaurant(restaurant);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable int id){
        return restaurantService.deleteRestaurant(id);
    }


    @GetMapping("/viewMenu")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<FoodDTO>> getFoods(@Param("restaurantName") String restaurantName) {
        logger.info("View the MENU");
        return new ResponseEntity<>(foodService.findByRestaurant(restaurantName), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addRestaurantManager")  public RestaurantManager addRestaurantManager(@RequestBody RestaurantManager restaurantManager){
        return restaurantManagerService.save(restaurantManager);
    }

}
