package com.capg.service;

import com.capg.dto.CustomerDTO;
import com.capg.entity.Customer;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerDTO customer);

    List<Customer> readCustomer();

    void updateCustomer(Customer customer);

    void modifyCustomer(String firstName, int customerID);

    void deleteCustomer(int customerId);

    Customer getCustomerById(int customerId);

    boolean findById(int customerId);
}
