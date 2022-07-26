package com.example.backendservicecore.service;

import com.example.backendservicecore.model.Admin;
import com.example.backendservicecore.model.Customer;
import com.example.backendservicecore.model.UserData;
import com.example.backendservicecore.model.dto.LoginDTO;
import com.example.backendservicecore.model.dto.UserDataDTO;
import com.example.backendservicecore.model.mapper.AdminMapper;
import com.example.backendservicecore.model.mapper.CustomerMapper;
import com.example.backendservicecore.model.mapper.UserDataMapper;
import com.example.backendservicecore.repository.AdminRepo;
import com.example.backendservicecore.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CustomerRepo customerRepo;


    private final static Logger logger = LoggerFactory.getLogger(UserDataService.class.getName());


    /**
     * Retrieves an user account from the database
     * @param username of the account
     * @return the DTO of the retrieved account
     */
    public UserDataDTO findByUsername(String username) {
        logger.info("Find account with username: {}", username);
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isPresent()) {
            //delete
            System.out.println(admin);
            return admin.map(value -> AdminMapper.getInstance().convertToDTO(value)).orElse(null);
        } else {
            Optional<Customer> customer = customerRepo.findByUsername(username);
            //delete
            System.out.println(customer);
            return customer.map(value -> CustomerMapper.getInstance().convertToDTO(value)).orElse(null);
        }
    }



    /**
     * Retrieves an user data account by LoginDTO (containing username and password)
     * @param loginDTO containing username and password
     * @return the DTO of the retrieved user data
     */
    public UserDataDTO getUserDataDTO(LoginDTO loginDTO) throws Exception {
        UserDataDTO userDataDTO = this.findByUsername(loginDTO.getUsername());
        if (userDataDTO == null) {
            logger.warn("No account with the username: {} was found", loginDTO.getUsername());
            throw new Exception("No userData found");
        }
        UserData userData = UserDataMapper.getInstance().convertFromDTO(userDataDTO);

        if (loginDTO.getPassword().equals(userData.getPassword()) ){
            return userDataDTO;
        }
        throw new Exception("Incorrect Password!");
    }



    /**
     * Logins an account
     * @param loginDTO containing username and password
     * @return the DTO of the logged in account
     */
    public UserDataDTO logIn(LoginDTO loginDTO) throws Exception {
        logger.info("Logged in {}", loginDTO.getUsername());
        return this.getUserDataDTO(loginDTO);
    }

}
