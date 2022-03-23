package com.capg.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="capg_product2")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;

    public Order(Integer productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(productId, order.productId) && Objects.equals(productName, order.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName);
    }

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }


}