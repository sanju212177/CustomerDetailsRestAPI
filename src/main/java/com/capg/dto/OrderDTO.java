package com.capg.dto;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    @NotNull(message = "product name cannot be null")
    @NotBlank(message = "product name cannot be blank")
    @Size(min=3,message= "Product Name should have atleast 3 characters")
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }


}
