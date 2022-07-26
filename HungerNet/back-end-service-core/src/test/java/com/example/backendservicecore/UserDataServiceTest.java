package com.example.backendservicecore;

import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.Customer;
import com.example.backendservicecore.model.dto.LoginDTO;
import com.example.backendservicecore.model.dto.UserDataDTO;
import com.example.backendservicecore.repository.AdminRepo;
import com.example.backendservicecore.repository.CustomerRepo;
import com.example.backendservicecore.service.UserDataService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserDataServiceTest {
    @Mock
    private AdminRepo administratorRepo;

    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private UserDataService userDataService;

    private final TestObject dummy = new TestObject();

    @Test
    public void testFindByUsernameAdmin() {
        Admin administrator = dummy.createTestAdmin();

        Mockito.when(administratorRepo.findByUsername(administrator.getUsername())).thenReturn(Optional.of(administrator));

        UserDataDTO foundUserDataDTO = userDataService.findByUsername(administrator.getUsername());

        Assertions.assertEquals(administrator.getUsername(), foundUserDataDTO.getUsername());

    }

    @Test
    public void testFindByUsernameCustomer() {
        Customer customer = dummy.createTestCustomer();

        Mockito.when(administratorRepo.findByUsername(customer.getUsername())).thenReturn(Optional.empty());
        Mockito.when(customerRepo.findByUsername(customer.getUsername())).thenReturn(Optional.of(customer));

        UserDataDTO foundUserDataDTO = userDataService.findByUsername(customer.getUsername());

        Assertions.assertEquals(customer.getUsername(), foundUserDataDTO.getUsername());
    }

    @Test(expected = Exception.class)
    public void testLoginNoUserData() throws Exception {
        Admin administrator = dummy.createTestAdmin();

        Mockito.when(administratorRepo.findByUsername(administrator.getUsername())).thenReturn(Optional.empty());
        Mockito.when(customerRepo.findByUsername(administrator.getUsername())).thenReturn(Optional.empty());

        userDataService.logIn(new LoginDTO(administrator.getUsername(), administrator.getPassword()));
    }
}
