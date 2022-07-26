package com.example.backendservicecore;

import com.example.backendservicecore.model.Restaurant;
import com.example.backendservicecore.model.mapper.RestaurantMapper;
import com.example.backendservicecore.repository.RestaurantRepo;
import com.example.backendservicecore.service.AdminService;
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
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepo restaurantRepo;

    @Mock
    private AdminService administratorService;


    @InjectMocks
    private RestaurantService restaurantService;

    private final TestObject testObject = new TestObject();

    @Test
    public void testSaveSuccess() {
        Restaurant restaurant = testObject.createTestRestaurant();

        Mockito.when(restaurantRepo.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(administratorService.findByUsername(Mockito.anyString())).thenReturn(restaurant.getAdmin());


        Mockito.when(restaurantRepo.save(Mockito.any(Restaurant.class))).then(returnsFirstArg());

        Assertions.assertDoesNotThrow(() -> restaurantService.save(RestaurantMapper.getInstance().convertToDTO(restaurant)));
    }

    @Test
    public void testSaveNoAdmin() {
        Restaurant restaurant = testObject.createTestRestaurant();

        Mockito.when(restaurantRepo.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(administratorService.findByUsername(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> restaurantService.save(RestaurantMapper.getInstance().convertToDTO(restaurant)));
    }

    @Test
    public void testSaveDuplicateName() {
        Restaurant restaurant = testObject.createTestRestaurant();

        Mockito.when(restaurantRepo.findByName(Mockito.anyString())).thenReturn(Optional.of(restaurant));

        Assertions.assertThrows(Exception.class, () -> restaurantService.save(RestaurantMapper.getInstance().convertToDTO(restaurant)));
    }
}
