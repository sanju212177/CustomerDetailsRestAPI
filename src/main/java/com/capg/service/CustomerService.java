package com.capg.service;

import com.capg.dto.CustomerDTO;
import com.capg.dto.OrderDTO;
import com.capg.entity.Customer;
import com.capg.entity.Order;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerDTO customer);

    List<Customer> readCustomer();

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);

    Customer getCustomerById(int customerId);

    boolean findById(int customerId);

    void createOrder(OrderDTO order);

    List<Order> readOrders();

    Order getOrderById(int productId);

    void updateOrder(Order order);

    void deleteOrder(int productId);
}
