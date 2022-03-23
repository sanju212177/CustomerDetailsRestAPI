package com.capg.service;

import com.capg.dto.CustomerDTO;
import com.capg.entity.Address;
import com.capg.entity.Customer;
import com.capg.entity.Order;
import com.capg.entity.User;
import com.capg.exception.CustomerNotFoundException;
import com.capg.exception.OrderNotFoundException;
import com.capg.repository.CustomerRepository;
import com.capg.repository.OrderRepository;
import com.capg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void createCustomer(CustomerDTO customer) {
        Customer customer1 = new Customer();
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setEmail(customer.getEmail());
        customer1.setCustomerType(customer.getCustomerType());
        customer1.setDateOfBirth(customer.getDateOfBirth());

        Address address = new Address();
        address.setStreet(customer.getAddress().getStreet());
        address.setCity(customer.getAddress().getCity());
        customer1.setAddress(address);

        List <Order> order =new ArrayList<>();
        for(int i=0;i<customer.getOrder().size();i++) {
            Order o = new Order();
            o.setProductName(customer.getOrder().get(i).getProductName());
            o.setProductId(customer.getOrder().get(i).getProductId());
            order.add(o);
        }
        customer1.setOrder(order);
        customerRepository.save(customer1);
    }

    @Override
    public List<Customer> readCustomer() {
        List<Customer> customerList = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customer -> customerList.add(customer));
        return customerList;
    }

    @Override
    public List<User> readUsers() {
        return new ArrayList<>(userRepository.findAll());
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
    public void deleteAProduct(Integer productId) throws OrderNotFoundException {
        Optional<Order>optionalOrder = orderRepository.findById(productId);
        if(optionalOrder.isPresent()) {
            orderRepository.deleteById(productId);
        }
        else
            throw new OrderNotFoundException("Delete Operation failed \n No Order Found with id: "+productId);
    }

}
