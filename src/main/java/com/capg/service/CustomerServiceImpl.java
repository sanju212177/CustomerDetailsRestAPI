package com.capg.service;

import com.capg.dto.CustomerDTO;
import com.capg.dto.OrderDTO;
import com.capg.entity.Address;
import com.capg.entity.Customer;
//import com.capg.entity.Users;
import com.capg.entity.Order;
import com.capg.exception.CustomerNotFoundException;
import com.capg.exception.OrderNotFoundException;
import com.capg.repository.CustomerRepository;
//import com.capg.repository.UsersRepository;
import com.capg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public void createCustomer(CustomerDTO customer) {
        Customer customer1 = new Customer();
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setEmail(customer.getEmail());
        customer1.setCustomerType(customer.getCustomerType());
        Address address = new Address();
        address.setStreet(customer.getAddress().getStreet());
        address.setCity(customer.getAddress().getCity());
        customer1.setAddress(address);
        customerRepository.save(customer1);
    }

    @Override
    public List<Customer> readCustomer() {
        List<Customer> customerList = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customer -> customerList.add(customer));
        return customerList;
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        try{
            if(customerRepository.existsById(customerId)) {
                customerRepository.deleteById(customerId);
            }
            else{
                throw new CustomerNotFoundException("No Customer with this ID exists.");
            }
        }
        catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        try{
            if(customerRepository.existsById(customerId)) {
                return customerRepository.findById(customerId).get();
            }
            else{
                throw new CustomerNotFoundException("No Customer with this ID exists.");
            }
        }
        catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean findById(int customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public void createOrder(OrderDTO order) {
        Order order1 = new Order();
        order1.setProductId(order.getProductId());
        order1.setProductName(order.getProductName());
        orderRepository.save(order1);
    }

    @Override
    public List<Order> readOrders() {
        List<Order> orderList = new ArrayList<Order>();
        orderRepository.findAll().forEach(order -> orderList.add(order));
        return orderList;
    }

    @Override
    public Order getOrderById(int productId) {
        try{
            if(orderRepository.existsById(productId)) {
                return orderRepository.findById(productId).get();
            }
            else{
                throw new OrderNotFoundException("No Order with this ID exists.");
            }
        }
        catch (OrderNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int productId) {
        try{
            if(orderRepository.existsById(productId)) {
                orderRepository.deleteById(productId);
            }
            else{
                throw new OrderNotFoundException("No Order with this ID exists.");
            }
        }
        catch (OrderNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
