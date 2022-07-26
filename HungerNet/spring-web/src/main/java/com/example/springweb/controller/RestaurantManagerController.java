package com.example.springweb.controller;

import com.example.backendservicecore.model.Food;
import com.example.backendservicecore.model.FoodCategory;
import com.example.backendservicecore.model.Order;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.dto.FoodDTO;
import com.example.backendservicecore.model.dto.OrderDTO;
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
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {
    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;


    private final static Logger logger = LoggerFactory.getLogger(RestaurantManagerController.class.getName());

    @GetMapping("/addMenu")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<FoodCategory>> getFoodCategories() {
        System.out.println(foodCategoryService.findAll());
        return new ResponseEntity<>(foodCategoryService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addMenus")
    public List<FoodCategory> saveFoodCategorys(@RequestBody List<FoodCategory> foodCategories){
        return foodCategoryService.saveFoodCategorys(foodCategories);
    }

    @PutMapping ("/updateMenu")
    public FoodCategory updateFoodCategory(@RequestBody FoodCategory foodCategory){
        return foodCategoryService.updateFoodCategory(foodCategory);
    }

    @DeleteMapping("/deleteMenu/{id}")
    public String deleteFoodCategory(@PathVariable int id){
        return foodCategoryService.deleteFoodCategory(id);
    }


    @GetMapping("/viewListFoods")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<FoodDTO>> getFoods(@Param("restaurantName") String restaurantName) {
        logger.info("View the MENU");
        return new ResponseEntity<>(foodService.findByRestaurant(restaurantName), HttpStatus.ACCEPTED);
    }


    @PostMapping("/addFood")
    public ResponseEntity<FoodDTO> addFood(@RequestBody(required = false) FoodDTO foodDTO) {
        logger.info("Add food {}", foodDTO.getName());
        return new ResponseEntity<>(foodService.save(foodDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteFood/{id}")
    public String deleteFood(@PathVariable int id){
        return foodService.deleteFood(id);
    }

    @PutMapping ("/update")
    public Food updateFood(@RequestBody Food food){
        return foodService.updateFood(food);
    }

    @GetMapping("/viewOrders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<OrderDTO>> getOrders() {
        System.out.println(orderService.findAll());
        return new ResponseEntity<>(orderService.findAll(),HttpStatus.ACCEPTED);
    }



    @PostMapping("/addOrder")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody(required = false) OrderDTO orderDTO) {
        logger.info("Add order {}", orderDTO.getIdOrder());
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id){
        return orderService.deleteOrder(id);
    }

    @PutMapping ("/updateOrder")
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

}
