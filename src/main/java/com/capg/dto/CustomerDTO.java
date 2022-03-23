package com.capg.dto;

import com.capg.CustomValidation.DOBValidation;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    @NotBlank(message = "First name cannot be blank")
    @Size(min=3,message= "First name should have atleast 3 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name should contain only alphabets")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(min=3,message= "First name should have atleast 3 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name should contain only alphabets")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Enter a valid email address")
    private String email;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    @NotNull(message = "Date of birth cannot be blank")
    @Past(message = "Date of birth cannot be in the future")
    @DOBValidation
    private LocalDate dateOfBirth;
    @Valid
    @NotNull(message = "Address cannot be null")
    private AddressDTO address;
    @Valid
    @NotNull(message = "Order details cannot be null")
    private List<OrderDTO> order;

    public CustomerDTO(){

    }

    public CustomerDTO(int customerId, String firstName, String lastName, String email, CustomerType customerType, LocalDate dateOfBirth, AddressDTO address, List<OrderDTO> order) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerType = customerType;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.order = order;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<OrderDTO> getOrder() {
        return order;
    }

    public void setOrder(List<OrderDTO> order) {
        this.order = order;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return customerId == that.customerId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && customerType == that.customerType && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(address, that.address) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, email, customerType, dateOfBirth, address, order);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", customerType=" + customerType +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", order=" + order +
                '}';
    }
}
