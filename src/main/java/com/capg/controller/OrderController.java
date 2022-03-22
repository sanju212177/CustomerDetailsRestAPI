package com.capg.controller;


import com.capg.dto.CustomerDTO;
import com.capg.dto.OrderDTO;
import com.capg.entity.Customer;
import com.capg.entity.Order;
import com.capg.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    private ResponseEntity<String> saveOrder(@Valid @RequestBody OrderDTO order) {
        customerService.createOrder(order);
        return new ResponseEntity<>("Order Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    private List<Order> getAllOrders() {
        return customerService.readOrders();
    }

    @GetMapping("/get/{productId}")
    private Order getOrder(@PathVariable("productId") int productId) {
        Order order = customerService.getOrderById(productId);
        return order;
    }

    @PutMapping("/update")
    private ResponseEntity<String> update(@RequestBody Order order) {
        customerService.updateOrder(order);
        return new ResponseEntity<String>("Order updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{productId}")
    private ResponseEntity<String> deleteOrder(@PathVariable("productId") int productId) {
        customerService.deleteOrder(productId);
        return new ResponseEntity<>("Order with given ID is Deleted", HttpStatus.OK);
    }

    @PatchMapping("/patch/{customerId}")
    private ResponseEntity<String> updatePatch(@PathVariable int productId, @RequestBody Order order) {
        customerService.updateOrder(customerService.getOrderById(productId));
        return new ResponseEntity<>("Customer details Modified", HttpStatus.ACCEPTED);



    }
}
