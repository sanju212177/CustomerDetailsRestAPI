package com.capg.controller;

import com.capg.dto.CustomerDTO;
import com.capg.entity.Customer;
import com.capg.service.CustomerService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    private ResponseEntity<String> saveCustomer(@Valid@RequestBody CustomerDTO customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>("Customer Details Entered Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    private List<Customer> getAllCustomers() {
        return customerService.readCustomer();
    }

    @GetMapping("/get/{customerId}")
    private Customer getCustomer(@PathVariable("customerId") int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return customer;
    }

    @PutMapping("/update")
    private ResponseEntity<String> update(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return new ResponseEntity<String>("Customer details updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    private ResponseEntity<String> deleteCustomer(@PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer with given ID is Deleted", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer productId){
        customerService.deleteAProduct(productId);
        return new ResponseEntity<>("Product deleted Successfully",HttpStatus.OK);

    }
    @PatchMapping("/patch/{customerId}")
    private ResponseEntity<String> updatePatch(@PathVariable int customerId, @RequestBody Customer customer) {
        Customer cus = customerService.getCustomerById(customerId);
        boolean needUpdate = false;
        if (StringUtils.hasLength(customer.getFirstName())) {
            cus.setFirstName(customer.getFirstName());
            needUpdate = true;
        }
        if (StringUtils.hasLength(customer.getLastName())) {
            cus.setLastName(customer.getLastName());
            needUpdate = true;
        }
        if (StringUtils.hasLength(String.valueOf(customer.getCustomerType()).equals("null") ? "" : String.valueOf(customer.getCustomerType()))) {
            cus.setCustomerType(customer.getCustomerType());
            needUpdate = true;
        }
        if (StringUtils.hasLength(customer.getEmail())) {
            cus.setEmail(customer.getEmail());
            needUpdate = true;
        }
        if (needUpdate) {
            customerService.updateCustomer(cus);
            return new ResponseEntity<>("Customer details Modified", HttpStatus.ACCEPTED);
        }
        return null;

    }
}