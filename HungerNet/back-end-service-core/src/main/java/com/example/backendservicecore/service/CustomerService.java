package com.example.backendservicecore.service;

import com.example.backendservicecore.model.Customer;
import com.example.backendservicecore.model.dto.CustomerDTO;
import com.example.backendservicecore.model.mapper.CustomerMapper;
import com.example.backendservicecore.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AdminService adminService;


    private final static Logger logger = LoggerFactory.getLogger(CustomerService.class.getName());



    /**
     * Searches for an customer in the database by username
     * @param username of the customer
     * @return the found customer
     */
    public Customer findByUsername(String username) {
        logger.info("Find customer: {}", username);

        Optional<Customer> customer = customerRepo.findByUsername(username);

        return customer.orElse(null);
    }



    /**
     * Registers a customer
     * @param customerDTO, the details of the customer
     * @return the registered customer
     */
    public CustomerDTO register(CustomerDTO customerDTO) {
        Customer c = CustomerMapper.getInstance().convertFromDTO(customerDTO);
        customerRepo.save(c);
        logger.info("Register customer: {}", customerDTO.getUsername());

        return CustomerMapper.getInstance().convertToDTO(c);
    }




    public Customer save(Customer customer) {
        logger.info("Customer {} saved to database", customer);
        Customer a = Customer.CustomerBuilder()
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .password(customer.getPassword())
                .build();
        return customerRepo.save(a);
    }


    public String deleteCustomer(int id){
        customerRepo.deleteById(id);
        return "customer removed !!" +id;
    }


    public List<Customer> saveCustomers(List<Customer> customers){
        return   customerRepo.saveAll(customers);
    }


    public List<Customer> getCustomers(){
        return customerRepo.findAll();

    }


    public Customer getCustomerById(int id){
        return customerRepo.findById(id).orElse(null);
    }


}
