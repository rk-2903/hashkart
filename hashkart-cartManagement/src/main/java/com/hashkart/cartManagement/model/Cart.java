package com.hashkart.cartManagement.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String orderDate;
    private String userId;
    @OneToMany(mappedBy = "cart")
    private List<CartProducts> cartProducts;
    private String totalAmount;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartProducts> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
