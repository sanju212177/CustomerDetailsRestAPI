package com.capg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddressDTO {
    @NotBlank(message = "Street can't be empty")
    @NotNull(message = "Street can't be empty")
    private String street;
    @NotBlank(message = "City can't be empty")
    @NotNull(message = "City can't be empty")
    private String city;
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public AddressDTO() {
    }

    public AddressDTO(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(street, that.street) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city);
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}