package com.capg.service;

import com.capg.dto.CustomerDTO;
import com.capg.entity.Customer;
import com.capg.entity.User;


import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerDTO customer);

    List<Customer> readCustomer();

    public List<User> readUsers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);

    Customer getCustomerById(int customerId);

    boolean findById(int customerId);

    void deleteAProduct(Integer productId);
}
