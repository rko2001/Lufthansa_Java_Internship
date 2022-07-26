package com.example.backendservicecore;

import com.example.backendservicecore.model.Food;
import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.dto.FoodDTO;
import com.example.backendservicecore.repository.FoodRepo;
import com.example.backendservicecore.service.FoodService;
import com.example.backendservicecore.service.RestaurantService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class FoodTest {
    @Mock
    private FoodRepo foodRepo;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private FoodService foodService;

    private final TestObject testObject = new TestObject();

    @Test
    public void testSave() {
        Food food = testObject.createTestFood();
        FoodDTO foodDTO = testObject.createTestFoodDTO();

        Mockito.when(restaurantService.findByName(Mockito.anyString())).thenReturn(food.getRestaurant());
        Mockito.when(foodRepo.findByNameAndRestaurant(Mockito.anyString(), Mockito.any(Restaurant.class)))
                .thenReturn(Optional.empty());

        Mockito.when(foodRepo.save(Mockito.any(Food.class))).then(returnsFirstArg());

        Assertions.assertDoesNotThrow(() -> foodService.save(foodDTO));
    }

    @Test
    public void testSaveNoRestaurant() {
        FoodDTO foodDTO = testObject.createTestFoodDTO();

        Mockito.when(restaurantService.findByName(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> foodService.save(foodDTO));
    }

    @Test
    public void testSaveDuplicateFood() {
        Food food = testObject.createTestFood();
        FoodDTO foodDTO = testObject.createTestFoodDTO();

        Mockito.when(restaurantService.findByName(Mockito.anyString())).thenReturn(food.getRestaurant());
        Mockito.when(foodRepo.findByNameAndRestaurant(Mockito.anyString(), Mockito.any(Restaurant.class)))
                .thenReturn(Optional.of(food));

        Assertions.assertThrows(Exception.class, () -> foodService.save(foodDTO));
    }
}
